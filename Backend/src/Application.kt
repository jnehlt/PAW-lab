package com.example.server

import com.example.server.auth.JwtConfig
import com.example.server.controllers.SessionController
import com.example.server.controllers.UserController
import com.example.server.database.dto.UserDTO
import com.example.server.database.model.User
import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.body
import kotlinx.html.h1
import kotlinx.html.head
import kotlinx.html.title
import org.jetbrains.exposed.sql.Database
import java.security.MessageDigest

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private lateinit var userController: UserController
private lateinit var sessionController: SessionController

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    initDB()
    setupControllers()

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    install(Authentication) {
        /**
         * Setup the JWT authentication to be used in [Routing].
         * If the token is valid, the corresponding [User] is fetched from the database.
         * The [User] can then be accessed in each [ApplicationCall].
         */
        jwt {
            verifier(JwtConfig.verifier)
            realm = "ktor.io"
            validate {
                val token = this.request.headers.get("Authorization")?.removePrefix("Bearer ")

                if (sessionController.checkTokenValid(token)) {
                    val userId = sessionController.getUSerIdByToken(token!!)
                    if (userId != null) {
                        val user = userController.getById(userId)
                        user?.let {
                            return@validate User(it.email, it.password)
                        }
                    }

                    null
                } else
                    null
            }
        }
    }

    routing {
        get("/") {
            call.respondHtml {
                head {
                    title { +"ClonelloServer" }
                }
                body {
                    h1 { +"Witaj na serwerze Clonello" }
                }
            }
        }

        authenticate {
            get("/users") {
                call.respond(userController.getAll())
            }
        }

        authenticate {
            delete("/users") {
                val userDTO = call.receive<UserDTO>()
                val user = userController.getByEmail(userDTO.email)
                userController.delete(userDTO)
                sessionController.delete(user?.id!!)
                call.respond(HttpStatusCode.NoContent)
            }
        }

        post("/users/sign") {
            val userDTO = call.receive<UserDTO>()
            if (userController.getByEmail(userDTO.email) == null) {
                userController.insert(userDTO)
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.Conflict)
            }
        }

        authenticate {
            put("/users") {
                try {
                    val token = this.context.request.headers.get("token")?.removePrefix("Bearer ")
                    val userId = sessionController.getUSerIdByToken(token!!)
                    val user = userController.getById(userId!!)
                    val userDTO = call.receive<UserDTO>()
                    userController.update(userDTO, user?.id!!)
                    call.respond(HttpStatusCode.Accepted)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }

        post("users/login") {
            try {
                val userCred = call.receive<UserPasswordCredential>()
                val currentUser = userController.getByEmail(userCred.name)
                val currentPassword = MessageDigest.getInstance("SHA-512").digest(userCred.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) }).toByteArray()

                currentUser?.let {
                    if (currentPassword.contentEquals(it.password)) {
                        val token = JwtConfig.makeToken(it)
                        sessionController.insert(it.id, token)
                        return@post call.respond(HttpStatusCode.Accepted, token)
                    } else {
                        return@post call.respond(HttpStatusCode.Unauthorized)
                    }
                }
                call.respond(HttpStatusCode.Unauthorized)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        authenticate {
            get("users/logout") {
                try {
                    val token = this.context.request.headers.get("token")?.removePrefix("Bearer ")
                    val userId = sessionController.getUSerIdByToken(token!!)
                    val user = userController.getById(userId!!)
                    val currentUser = userController.getByEmail(user?.email!!)
                    currentUser?.let {
                        sessionController.delete(it.id)
                    }

                    call.respond(HttpStatusCode.NoContent)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}

fun setupControllers() {
    userController = UserController()
    sessionController = SessionController()
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    config.schema = "postgres"
    val ds = HikariDataSource(config)
    Database.connect(ds)
}


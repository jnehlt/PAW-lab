package com.example.server

import com.example.server.auth.JwtConfig
import com.example.server.controllers.SessionController
import com.example.server.controllers.UserController
import com.example.server.database.dto.UserDTO
import com.example.server.database.model.User
import com.example.server.repositories.UserRepo
import com.example.server.repositories.UserRepoImpl
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
import kotlinx.html.*
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

    var userRepo: UserRepo = UserRepoImpl()
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
                val name = it.payload.getClaim("name").asString()
                val password = MessageDigest.getInstance("SHA-512").digest(it.payload.getClaim("password").toString().toByteArray()).fold("", { str, it -> str + "%02x".format(it) }).toByteArray()
                if (name != null && password != null) {
                    User(name, password)
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
                    h2 {
                        +"GET - pobranie wszystkich uzytkowników"
                        p {
                            +"../users"
                        }
                    }

                    h2 {
                        +"POST - utworzenie nowego użytkownika"
                        p {
                            +"../users"
                        }
                        p {
                            +"   argumenty:"
                        }
                        p {
                            +"""
                                {
                                    "email":"{string}",
                                    "password":"{string}"
                                }
                            """.trimIndent()
                        }
                    }
                }
            }
        }

        authenticate {
            get("/users") {
                call.respond(userController.getAll())
            }
        }

        post("/users") {
            val userDTO = call.receive<UserDTO>()
            val id = userController.insert(userDTO)
            call.respond(HttpStatusCode.Created, id)
        }

        authenticate {
            put("/users/{id}") {
                try {
                    val userDTO = call.receive<UserDTO>()
                    val id = call.parameters["id"]?.toInt() ?: 0
                    userController.update(userDTO, id)
                    call.respond(HttpStatusCode.Accepted)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }

        post("users/login") {
            try {
                val userCred = call.receive<UserPasswordCredential>()
                val currentUser = userController.getAll().find { user -> user.email.equals(userCred.name) }
                val currentPassword = MessageDigest.getInstance("SHA-512").digest(userCred.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) }).toByteArray()

                if (currentPassword.contentEquals(currentUser?.password!!)) {

                    val token = JwtConfig.makeToken(currentUser)
                    call.respond(HttpStatusCode.Accepted, token)
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        authenticate {
            post("users/logout") {
                try {
                    val userDTO = call.receive<UserDTO>()
                    val currentUser = userController.getAll().find { user -> user.email.equals(userDTO.email) }

                    sessionController.delete(currentUser!!.id)
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


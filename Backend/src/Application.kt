package com.example.server

import com.example.server.controllers.SessionController
import com.example.server.controllers.UserController
import com.example.server.database.dto.UserDTO
import com.fasterxml.jackson.databind.SerializationFeature
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.html.respondHtml
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.*
import kotlinx.html.*
import org.jetbrains.exposed.sql.Database
import java.lang.Exception
import java.security.MessageDigest
import java.util.*

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
                                    "firstName":"{string}",
                                    "lastName":"{string}",
                                    "password":"{string}"
                                }
                            """.trimIndent()
                        }
                    }
                }
            }
        }

        get("/users") {
            call.respond(userController.getAll())
        }

        post("/users") {
            val userDTO = call.receive<UserDTO>()
            val id = userController.insert(userDTO)
            call.respond(HttpStatusCode.Created,id)
        }

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

        post("users/login") {
            try {
                val userDTO = call.receive<UserDTO>()
                val currentUser = userController.getAll().find { user -> user.email.equals(userDTO.email) }
                val currentPassword =  MessageDigest.getInstance("SHA-512").digest(userDTO.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) }).toByteArray()

                if (currentPassword.contentEquals(currentUser?.password!!)){
                    sessionController.insert(currentUser.id)
                    call.respond(HttpStatusCode.Accepted)
                }else{
                    call.respond(HttpStatusCode.BadRequest)
                }
            }catch (e : Exception){
                call.respond(HttpStatusCode.BadRequest)
            }
        }

        post("users/logout") {
            try{
                val userDTO = call.receive<UserDTO>()
                val currentUser = userController.getAll().find { user -> user.email.equals(userDTO.email) }

                sessionController.delete(currentUser!!.id)
                call.respond(HttpStatusCode.NoContent)
            }catch (e : Exception){
                call.respond(HttpStatusCode.BadRequest)
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


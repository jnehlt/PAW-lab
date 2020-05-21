package com.example.server

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
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.html.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private lateinit var userController: UserController

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
            userController.insert(userDTO)
            call.respond(HttpStatusCode.Created)
        }
    }
}

fun setupControllers() {
    userController = UserController()
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    config.schema = "postgres"
    val ds = HikariDataSource(config)
    Database.connect(ds)
}


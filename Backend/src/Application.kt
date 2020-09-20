package com.example.server

import com.example.server.auth.JwtConfig
import com.example.server.controllers.CardController
import com.example.server.controllers.ListController
import com.example.server.controllers.SessionController
import com.example.server.controllers.UserController
import com.example.server.database.dto.CardDTO
import com.example.server.database.dto.ListDTO
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
import java.time.Duration

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

private lateinit var userController: UserController
private lateinit var sessionController: SessionController
private lateinit var listController: ListController
private lateinit var cardController: CardController


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

    install(ForwardedHeaderSupport)
    install(DefaultHeaders)
    install(CORS)
    {
        method(HttpMethod.Options)
        method(HttpMethod.Get)
        method(HttpMethod.Post)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.AccessControlAllowHeaders)
        header(HttpHeaders.ContentType)
        header(HttpHeaders.AccessControlAllowOrigin)
        allowCredentials = true
        anyHost()
        maxAge = Duration.ofDays(1)
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
                    val session = sessionController.getSessionByToken(token!!)
                    val user = userController.getById(session?.userId!!)
                    user?.let {
                        return@validate User(it.email, it.password)
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

        post("/users/sign") {
            val userDTO = call.receive<UserDTO>()
            if (userController.getByEmail(userDTO.email) == null) {
                userController.insert(userDTO)
                call.respond(HttpStatusCode.Created)
            } else {
                call.respond(HttpStatusCode.Conflict)
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
                    val token = this.context.request.headers.get("Authorization")?.removePrefix("Bearer ")
                    val session = sessionController.getSessionByToken(token!!)
                    val user = userController.getById(session?.userId!!)
                    val currentUser = userController.getByEmail(user?.email!!)
                    currentUser?.let {
                        sessionController.delete(it.id)
                    }

                    call.respond(HttpStatusCode.NoContent)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            get("/users") {
                call.respond(userController.getAll())
            }

            delete("/users") {
                val userDTO = call.receive<UserDTO>()
                val user = userController.getByEmail(userDTO.email)
                userController.delete(userDTO)
                sessionController.delete(user?.id!!)
                call.respond(HttpStatusCode.NoContent)
            }

            put("/users") {
                try {
                    val token = this.context.request.headers.get("Authorization")?.removePrefix("Bearer ")
                    val session = sessionController.getSessionByToken(token!!)
                    val user = userController.getById(session?.userId!!)
                    val userDTO = call.receive<UserDTO>()
                    userController.update(userDTO, user?.id!!)
                    call.respond(HttpStatusCode.Accepted)
                } catch (e: Exception) {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }

        ///lists
        authenticate {
            get("/lists"){
                try {
                    val token = this.context.request.headers.get("Authorization")?.removePrefix("Bearer ")
                    if (sessionController.checkTokenValid(token)) {
                        val session = sessionController.getSessionByToken(token!!)
                        val user = userController.getById(session?.userId!!)
                        user?.let {
                            return@get call.respond(listController.getAll(user.id))
                        }
                    }
                }catch (e : Exception){
                   var a= e.message
                }
            }

            get("/lists/{id}"){
                val id = call.parameters.get("id")?.toInt()
                id?.let {
                    val list = listController.getById(it)
                    call.respond(HttpStatusCode.OK, list!!)
                    return@get
                }
                call.respond(HttpStatusCode.NotFound)
            }

            post("/lists") {
                val dto = call.receive<ListDTO>()
                listController.insert(dto)
                call.respond(HttpStatusCode.Created)
            }

            delete("/lists/{id}"){
                val id = call.parameters.get("id")?.toInt()
                id?.let {
                    listController.delete(it)
                    call.respond(HttpStatusCode.OK)
                    return@delete
                }
                call.respond(HttpStatusCode.NotFound)
            }

            put("/lists/{id}") {
                val id = call.parameters.get("id")?.toInt()
                val dto = call.receive<ListDTO>()
                id?.let {
                    listController.updateName(id,dto.name)
                    call.respond(HttpStatusCode.NoContent)
                    return@put
                }
                call.respond(HttpStatusCode.NotFound)
            }
        }

        //cards
        authenticate {
            get("/lists/{id}/cards"){
                val id = call.parameters.get("id")?.toInt()
                id?.let {
                    call.respond(cardController.getAllByList(it))
                }
                return@get
            }

            get("/cards/{id}"){
                val id = call.parameters.get("id")?.toInt()
                id?.let {
                    val card = cardController.getById(it)
                    call.respond(HttpStatusCode.OK, card!!)
                    return@get
                }
                call.respond(HttpStatusCode.NotFound)
            }

            post("/cards") {
                val dto = call.receive<CardDTO>()
                cardController.insert(dto)
                call.respond(HttpStatusCode.Created)
            }

            delete("/cards/{id}"){
                val id = call.parameters.get("id")?.toInt()
                id?.let {
                    cardController.delete(it)
                    call.respond(HttpStatusCode.OK)
                    return@delete
                }
                call.respond(HttpStatusCode.NotFound)
            }

            put("/cards/{id}") {
                val id = call.parameters.get("id")?.toInt()
                val dto = call.receive<CardDTO>()
                id?.let {
                    cardController.updateName(id,dto)
                    call.respond(HttpStatusCode.NoContent)
                    return@put
                }
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}

fun setupControllers() {
    userController = UserController()
    sessionController = SessionController()
    listController = ListController()
    cardController = CardController()
}

fun initDB() {
    val config = HikariConfig("/hikari.properties")
    config.schema = "postgres"
    val ds = HikariDataSource(config)
    Database.connect(ds)
}


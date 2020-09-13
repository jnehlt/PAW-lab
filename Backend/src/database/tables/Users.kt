package com.example.server.database.tables

import io.ktor.auth.*
import org.jetbrains.exposed.sql.Table

object Users : Table("public.Users"), Principal {
    val id = integer("id").autoIncrement().primaryKey()
    val firstName = text("firstName")
    val lastName = text("lastName")
    val email = text("email")
    val password = binary("password", 512)
    val createDate = datetime("createDate")
}
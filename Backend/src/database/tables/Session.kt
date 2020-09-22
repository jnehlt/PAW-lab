package com.example.server.database.tables

import org.jetbrains.exposed.sql.Table

object Session : Table("public.Session") {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = integer("userId")
    val token = text("token")
    val expirationDate = datetime("expirationDate")
    val createDate = datetime("createDate")
}


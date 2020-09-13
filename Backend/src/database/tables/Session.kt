package com.example.server.database.tables

import org.jetbrains.exposed.sql.Table

object Session : Table("public.Session") {
    val id = integer("id").autoIncrement().primaryKey()
    val userId = integer("userId")
    val createDate = long("createDate")
    val expirationDate = long("expirationDate")
}


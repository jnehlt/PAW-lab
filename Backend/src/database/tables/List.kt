package com.example.server.database.tables

import org.jetbrains.exposed.sql.Table

object List : Table("public.List") {
    val id = integer("id").autoIncrement().primaryKey()
    val arrayId = integer("arrayId")
    val name = text("name")
    val userId = integer("userId")
    val createDate = datetime("createDate")
}
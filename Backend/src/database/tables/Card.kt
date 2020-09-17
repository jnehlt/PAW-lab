package com.example.server.database.tables

import com.example.server.database.tables.Session.autoIncrement
import com.example.server.database.tables.Session.primaryKey
import org.jetbrains.exposed.sql.Table

object Card : Table("public.Card") {
    val id = integer("id").autoIncrement().primaryKey()
    val listId = integer("listId")
    val name = text("name")
    val content = text("content").nullable()
    val description = text("description").nullable()
    val complete = bool("complete").nullable()
    val deadline = datetime("deadline").nullable()
    val createDate = datetime("createDate")
}
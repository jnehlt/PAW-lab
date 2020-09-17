package com.example.server.controllers

import com.example.server.database.dto.ListDTO
import com.example.server.database.tables.List as Lists
import com.example.server.database.model.List
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime

class ListController {

    init {
        transaction {
            SchemaUtils.create(Lists)
        }
    }

    fun getAll() = transaction {
        Lists.selectAll().mapNotNull { toList(it) }
    }

    fun getById(id: Int) = transaction {
        Lists.select {
            Lists.id eq id
        }.mapNotNull { toList(it) }.singleOrNull()
    }

    fun delete(id: Int) = transaction {
        Lists.deleteWhere {
            Lists.id eq id
        }
    }

    fun updateName(id: Int, newName: String) = transaction {
        Lists.update({
            Lists.id eq id
        }) {
            it[name] = newName
        }
    }

    fun insert(list: ListDTO) = transaction {
        Lists.insert {
            it[arrayId] = list.arrayId
            it[name] = list.name
            it[userId] = list.userId
            it[createDate] = DateTime.now()
        }
    }

    private fun toList(data: ResultRow) =
            List(data[Lists.id],
                    data[Lists.arrayId],
                    data[Lists.name],
                    data[Lists.userId],
                    data[Lists.createDate])
}
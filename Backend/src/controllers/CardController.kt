package com.example.server.controllers

import com.example.server.database.dto.CardDTO
import com.example.server.database.model.Card
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import com.example.server.database.tables.Card as Cards

class CardController {

    init {
        transaction {

            SchemaUtils.create(Cards)
        }
    }

    fun getAllByList(listId : Int) = transaction {
        Cards.select {
            Cards.listId eq listId
        }.mapNotNull { toCard(it) }
    }

    fun getById(id: Int) = transaction {
        Cards.select {
            Cards.id eq id
        }.mapNotNull { toCard(it) }.singleOrNull()
    }

    fun delete(id: Int) = transaction {
        Cards.deleteWhere {
            Cards.id eq id
        }
    }

    fun updateName(id: Int, dto : CardDTO) = transaction {
        Cards.update({
            Cards.id eq id
        }) {
            it[name] = dto.name
            it[content] = dto.content
            it[description] = dto.description
            it[listId] = dto.listId
            it[complete] = dto.complete
            it[deadline] = dto.deadline
        }
    }

    fun insert(data: CardDTO) = transaction {
        Cards.insert {
            it[listId] = data.listId
            it[name] = data.name
            it[content] = data.content
            it[description] = data.description
            it[complete] = data.complete
            it[deadline] = data.deadline
            it[createDate] = DateTime.now()
        }
    }

    private fun toCard(data: ResultRow) = Card(
            data[Cards.id],
            data[Cards.name],
            data[Cards.content],
            data[Cards.description],
            data[Cards.complete],
            data[Cards.deadline],
            data[Cards.createDate])

}
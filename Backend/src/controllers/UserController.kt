package com.example.server.controllers

import com.example.server.database.dto.UserDTO
import com.example.server.database.model.User
import com.example.server.database.tables.Users
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.LocalDateTime
import java.security.MessageDigest

class UserController {

    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    fun getAll() = transaction {
        transaction {
            Users.selectAll().mapNotNull { toUser(it) }
        }
    }


    fun getById(id: Int) = transaction {
        Users.select {
            Users.id eq id
        }.mapNotNull {
            toUser(it)
        }.singleOrNull()
    }


    fun getByEmail(email: String) = transaction {
        Users.select {
            Users.email eq email
        }.mapNotNull { toUser(it) }.singleOrNull()
    }

    fun insert(user: UserDTO) {
        transaction {
            val pass = MessageDigest.getInstance("SHA-512").digest(user.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) })
            Users.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[password] = pass.toByteArray()
                it[email] = user.email
                it[createDate] = LocalDateTime().toDateTime()
            }
        }
    }

    fun update(user: UserDTO, id: Int) {
        transaction {
            Users.update({ Users.id eq id }) {
                val pass = MessageDigest.getInstance("SHA-512").digest(user.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) })
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[password] = pass.toByteArray()
            }
        }
    }

    fun delete(user: UserDTO) {
        transaction {
            Users.deleteWhere { Users.email eq user.email }
        }
    }

    private fun toUser(row: ResultRow): User {
        val user = User(
                email = row[Users.email],
                password = row[Users.password],
                firstName = row[Users.firstName],
                lastName = row[Users.lastName],
                createDate = row[Users.createDate]
        )
        user.id = row[Users.id]
        return user
    }
}
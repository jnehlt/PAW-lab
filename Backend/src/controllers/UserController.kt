package com.example.server.controllers

import com.example.server.database.dto.UserDTO
import com.example.server.database.model.User
import com.example.server.database.tables.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.LocalDateTime
import java.security.MessageDigest

class UserController {

    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    fun getAll(): ArrayList<User> {
        val users = arrayListOf<User>()
        transaction {
            Users.selectAll().map {
                users.add(User(
                        it[Users.firstName]
                        , it[Users.lastName]
                        , it[Users.email]
                ))
            }
        }
        return users
    }

    fun insert(user: UserDTO) {
        transaction {
            var pass = MessageDigest.getInstance("SHA-512").digest(user.password.toByteArray()).fold("", { str, it -> str + "%02x".format(it) })
            Users.insert {
                it[firstName] = user.firstName
                it[lastName] = user.lastName
                it[password] = pass.toByteArray()
                it[email] = user.email
                it[createDate] = LocalDateTime.now().toDateTime()
            }
        }
    }
}
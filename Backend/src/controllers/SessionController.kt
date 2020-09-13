package com.example.server.controllers

import com.example.server.database.tables.Session
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class SessionController {

    init {
        transaction {
            SchemaUtils.create(Session)
        }
    }

    fun insert(userIdSession: Int, userToken: String) {
        transaction {
            Session.insert {
                it[userId] = userIdSession
                it[createDate] = Calendar.getInstance().timeInMillis
                it[token] = userToken
                it[expirationDate] = run {
                    val date = Calendar.getInstance()
                    date.add(Calendar.HOUR, 4)
                    date.timeInMillis
                }
            }
        }
    }

    fun delete(userId: Int) {
        transaction {
            Session.deleteWhere { Session.userId eq userId }
        }
    }

    fun checkTokenValid(token: String?) =
            transaction {
                if (token != null) {
                    val session = Session.select { Session.token eq token }
                            .mapNotNull { toSession(it) }
                            .singleOrNull()

                    session?.let {
                        return@transaction it.expirationDate > Calendar.getInstance().timeInMillis
                    }

                    return@transaction false
                } else {
                    return@transaction false
                }
            }

    fun getUSerIdByToken(token: String) =
            transaction {
                val session = Session.select { Session.token eq token }
                        .mapNotNull { toSession(it) }
                        .singleOrNull()
                return@transaction session?.userId
            }


    fun toSession(it: ResultRow) = com.example.server.database.model.Session(
            id = it[Session.id],
            token = it[Session.token],
            userId = it[Session.userId],
            expirationDate = it[Session.expirationDate],
            createdDate = it[Session.createDate]
    )
}
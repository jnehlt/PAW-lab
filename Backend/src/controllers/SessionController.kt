package com.example.server.controllers

import com.example.server.database.tables.Session
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
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
                it[createDate] = DateTime.now()
                it[token] = userToken
                it[expirationDate] = run {
                    val date = DateTime.now()
                    date.plusHours(4)
                    date
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
                    val session = getSessionByToken(token)

                    session?.let {
                        return@transaction it.expirationDate.millis > Calendar.getInstance().timeInMillis
                    }

                    return@transaction false
                } else {
                    return@transaction false
                }
            }

    fun getSessionByToken(token: String) =
            transaction {
                Session.select { Session.token eq token }
                        .mapNotNull { toSession(it) }
                        .firstOrNull()
            }

    private fun toSession(it: ResultRow) = com.example.server.database.model.Session(
            id = it[Session.id],
            userId = it[Session.userId],
            token = it[Session.token],
            expirationDate = it[Session.expirationDate],
            createdDate = it[Session.createDate]
    )
}
package com.example.server.controllers

import com.example.server.database.tables.Session
import com.example.server.database.tables.Users
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class SessionController {

    init {
        transaction {
            SchemaUtils.create(Users)
        }
    }

    fun insert(userIdSession : Int){
        transaction {
            Session.insert {
                it[userId] = userIdSession
                it[createDate] = Calendar.getInstance().timeInMillis
                it[expirationDate] = run {
                    val cal = Calendar.getInstance()
                    cal.add(Calendar.HOUR,4)
                    cal.timeInMillis
                }
            }
        }
    }

    fun delete(userIdSession: Int){
        transaction {
            Session.deleteWhere { Session.userId eq userIdSession }
        }
    }
}
package com.example.server.database.model

import org.joda.time.Hours
import java.util.*

data class Session(
        val id: Int,
        val userId: Int,
        val createdDate: Long = Calendar.getInstance().timeInMillis,
        val expirationDate: Long = run {
            val cal = Calendar.getInstance()
            cal.add(Calendar.HOUR, 4)
            cal.timeInMillis
        }
)
package com.example.server.database.model

import java.util.*

data class Session(
        val id: Int,
        val userId: Int,
        val token: String,
        val expirationDate: Long,
        val createdDate: Long = Calendar.getInstance().timeInMillis
)
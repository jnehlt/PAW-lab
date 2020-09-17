package com.example.server.database.model

import org.joda.time.DateTime
import java.util.*

data class Session(
        val id: Int,
        val userId: Int,
        val token: String,
        val expirationDate: DateTime,
        val createdDate: DateTime
)
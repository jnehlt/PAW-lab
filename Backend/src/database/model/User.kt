package com.example.server.database.model

import io.ktor.auth.*
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

data class User(
        val firstName: String?,
        val lastName: String?,
        val email: String,
        val password: ByteArray,
        val createDate: DateTime
) : Principal {
    constructor(email: String, password: ByteArray) : this(null, null, email, password, LocalDateTime().toDateTime())

    var id: Int = 0
}
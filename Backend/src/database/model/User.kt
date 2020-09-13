package com.example.server.database.model

import io.ktor.auth.*
import java.util.*

data class User(
        val firstName: String?,
        val lastName: String?,
        val email: String,
        val password: ByteArray,
        val createDate: Long
) : Principal {
    constructor(email: String, password: ByteArray) : this(null, null, email, password, Calendar.getInstance().timeInMillis)

    var id: Int = 0
}
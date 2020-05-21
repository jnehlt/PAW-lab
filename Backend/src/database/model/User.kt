package com.example.server.database.model

import org.joda.time.DateTime

data class User(
        val id: Int,
        val firstName: String,
        val lastName: String,
        val email: String,
        val password: ByteArray,
        val createDate: DateTime
) {
    constructor(firstName: String, lastName: String, email: String) : this(Int.MIN_VALUE, firstName, lastName, email, byteArrayOf(), DateTime(Long.MIN_VALUE))
}
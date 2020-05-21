package com.example.server.database.dto

data class UserDTO(
        val firstName: String,
        val lastName: String,
        val password: String,
        val email: String
)
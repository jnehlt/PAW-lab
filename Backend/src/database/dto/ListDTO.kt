package com.example.server.database.dto

data class ListDTO(
        val name: String,
        val userId: Int,
        val arrayId : Int = 0
)
package com.example.server.database.dto

import org.joda.time.DateTime

data class CardDTO(
        val listId : Int,
        val name : String,
        val content : String?,
        val description : String?,
        val complete : Boolean?,
        val deadline : DateTime?
)
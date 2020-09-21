package com.example.server.database.model

import org.joda.time.DateTime

data class List(
        val id : Int,
        val arrayId : Int,
        val name : String,
        val userId : Int,
        val createDate : DateTime
)
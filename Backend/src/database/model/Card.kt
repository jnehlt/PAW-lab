package com.example.server.database.model

import org.joda.time.DateTime

data class Card(
        val listId : Int,
        val name : String,
        val content : String?,
        val description : String?,
        val complete : Boolean?,
        val deadline : DateTime?,
        var createDate : DateTime = DateTime.now()
) {
    var id : Int = 0
}
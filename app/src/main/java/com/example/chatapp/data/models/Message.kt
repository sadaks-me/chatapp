package com.example.chatapp.data.models

import androidx.room.Entity

@Entity(tableName = "message", primaryKeys = ["id"])
data class Message(
    val id: Int = 0,
    val content: String,
    val timestamp: Long,
    val isSent: Boolean
)
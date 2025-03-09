package com.shetab.main.model

data class ChatMessage(
    val sender: String,
    val message: String,
    val isMe: Boolean
)

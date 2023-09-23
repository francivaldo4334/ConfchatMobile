package br.com.confchat.mobile.data.network.dto

data class ChatSendDto(
    val fromUserId: String,
    val message: String,
    val privateKey: String,
    val publicKey: String
)
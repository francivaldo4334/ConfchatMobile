package br.com.confchat.mobile.data.network.response

data class MessageApi(
    val content: String,
    val createAt: String,
    val read: Boolean,
    val userId: String,
    val userName: String
)
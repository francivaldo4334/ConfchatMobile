package br.com.confchat.mobile.data.network.response

data class ContactApi(
    val chatId: Int,
    val encripted: Boolean,
    val name: String,
    val previewMessage: String,
    val urlImage: String?,
    val userId: String
)
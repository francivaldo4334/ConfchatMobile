package br.com.confchat.mobile.veiwmodel.model

data class ContactViewModel(
    val urlImg: String,
    val name: String,
    val previewMessage: String,
    val coutNewsMessage: Int,
    val isEncripted: Boolean,
    val isOnline: Boolean
)
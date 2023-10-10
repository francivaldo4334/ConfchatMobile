package br.com.confchat.mobile.presenter.veiwmodel.model

data class ContactViewModel(
    val id: String,
    val urlImg: String,
    val name: String,
    val previewMessage: String,
    val coutNewsMessage: Int,
    val isEncripted: Boolean,
    val isOnline: Boolean,
    val chatId:Int = 0,
)
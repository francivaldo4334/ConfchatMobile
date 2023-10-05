package br.com.confchat.mobile.veiwmodel.model

data class Login(
    var deviceId: String = "",
    var deviceName: String = "",
    var loginOrEmail: String = "",
    var password: String = "",
    var totpCode: String = ""
)
package br.com.confchat.mobile.data.network.dto

class LoginDto constructor(
    var deviceId: String = "",
    var deviceName: String = "",
    var loginOrEmail: String = "",
    var password: String = "",
    var totpCode: String = "",
) {
}
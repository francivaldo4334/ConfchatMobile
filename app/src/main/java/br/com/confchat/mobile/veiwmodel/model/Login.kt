package br.com.confchat.mobile.veiwmodel.model

import br.com.confchat.mobile.data.network.dto.LoginDto

data class Login(
    var deviceId: String = "",
    var deviceName: String = "",
    var loginOrEmail: String = "",
    var password: String = "",
    var totpCode: String = ""
)
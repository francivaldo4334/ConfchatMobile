package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.dto.confchat.LoginDto
import br.com.confchat.mobile.presenter.veiwmodel.model.Login

fun Login.toDto(): LoginDto {
    return LoginDto(
        deviceId = this.deviceId,
        deviceName = this.deviceName,
        loginOrEmail = this.loginOrEmail,
        password = this.password,
        totpCode = this.totpCode
    )
}
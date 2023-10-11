package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.dto.confchat.ResetPasswordDto
import br.com.confchat.mobile.presenter.veiwmodel.model.ResetPassword

fun ResetPassword.toDto():ResetPasswordDto{
    return ResetPasswordDto(
        code = this.code,
        email = this.email,
        newPassword = this.newPassword
    )
}
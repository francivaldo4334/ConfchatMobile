package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.dto.confchat.RegisterDto
import br.com.confchat.mobile.presenter.veiwmodel.model.Register

fun Register.toDto() : RegisterDto {
    val register = RegisterDto()
    val regex = Regex("(\\d{2})(\\d{2})(\\d{4})")
    register.birthDay = this.birthDay.replace(regex,"$3-$2-$1")
    register.email = this.email
    register.login = this.login
    register.name = this.name
    register.password = this.password
    return register
}
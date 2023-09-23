package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.veiwmodel.model.Register

fun Register.toDto() : RegisterDto{
    val register = RegisterDto()
    register.birthDay = this.birthDay
    register.email = this.email
    register.login = this.login
    register.name = this.name
    register.password = this.password
    return register
}
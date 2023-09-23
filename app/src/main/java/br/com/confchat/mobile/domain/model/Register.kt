package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.veiwmodel.model.Register

fun Register.toDto() : RegisterDto{
    val register = RegisterDto()
    val regex = Regex("(\\d{4})(\\d{2})(\\d{2})")
    register.birthDay = this.birthDay.replace(regex,"$1-$2-$3")
    register.email = this.email
    register.login = this.login
    register.name = this.name
    register.password = this.password
    return register
}
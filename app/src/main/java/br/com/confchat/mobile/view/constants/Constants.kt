package br.com.confchat.mobile.view.constants

import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.Register

object Route {
    val VerificationCode = "VerificationCode"
    val BirthDay: String = "BirthDay"
    val Logup: String = "Logup"
    val Login = "Login"
}
object AuthDoc {
    var login:Login = Login()
    var register:Register = Register()
}
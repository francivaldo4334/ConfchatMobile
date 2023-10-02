package br.com.confchat.mobile.view.constants

import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.Register

object Route {
    val Profile = "Profile"
    val Merchant = "Merchant"
    val Add = "Add"
    val AnonymousChat = "AnonymousChat"
    val Chat: String = "Chat/{contactId}"
    val Contact: String = "Contact"
    val VerificationCode = "VerificationCode"
    val BirthDay: String = "BirthDay"
    val Logup: String = "Logup"
    val Login = "Login"
}
object AuthDoc {
    var login:Login = Login()
    var register:Register = Register()
}
package br.com.confchat.mobile.view.constants

import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.Register
object RoutePay{
    val InsertValue = "InsertValue"
    val CardInform = "CardInform"
    val Custumer = "Custumer"
}
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
class InformCardPayment{
    var carNumber = "XXXX XXXX XXXX XXXX"
    var nameAndLastName = "Name and last name"
    var validationMonth = ""
    var validationYear = ""
    var cvv = ""
    var installments = 0
    var cpf = ""
    var email = ""
}
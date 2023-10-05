package br.com.confchat.mobile.veiwmodel.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class PaymentCreditCard (
    val name : String,
    val cpf : String,
    val email: String,
    val amont : Int,
    val expirationCard: String,
    val numberCard:String,
    val nameOnCard:String,
    val cvv:String
)
package br.com.confchat.mobile.presenter.veiwmodel.model

data class ResetPassword(
    var code: String,
    var email: String,
    var newPassword: String
)
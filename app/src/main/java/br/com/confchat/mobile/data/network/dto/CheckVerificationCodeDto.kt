package br.com.confchat.mobile.data.network.dto

data class CheckVerificationCodeDto(
    val code: String,
    val email: String
)
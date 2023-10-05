package br.com.confchat.mobile.data.network.dto.confchat

data class CheckVerificationCodeDto(
    val code: String,
    val email: String
)
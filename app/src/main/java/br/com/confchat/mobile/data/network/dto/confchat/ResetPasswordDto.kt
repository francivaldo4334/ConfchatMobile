package br.com.confchat.mobile.data.network.dto.confchat

data class ResetPasswordDto(
    val code: String,
    val email: String,
    val newPassword: String
)
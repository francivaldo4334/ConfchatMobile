package br.com.confchat.mobile.data.network.response.confchat

data class ResponseApi<T>(
    val content: T,
    val status: Int
)
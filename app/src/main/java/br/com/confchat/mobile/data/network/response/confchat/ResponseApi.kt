package br.com.confchat.mobile.data.network.response.confchat


open class ResponseApi<T>(
    val content: T,
    val status: Int
)
class ResponseApiString(content: String, status: Int) : ResponseApi<String>(content, status) {}
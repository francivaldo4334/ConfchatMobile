package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.service.ApiConfchatService

class ChatApiRepository constructor(private val api: ApiConfchatService) : IChatApiRepository{
    override fun send(it: ChatSendDto): ResponseApi<String> {
        val call = api.send(it)
        try {
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!!
            }
            return response.body()!!
        }
        catch (e:Exception){
            return ResponseApi("Error",503)
        }
    }
}
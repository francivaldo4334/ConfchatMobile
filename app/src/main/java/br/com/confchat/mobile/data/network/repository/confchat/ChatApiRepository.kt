package br.com.confchat.mobile.data.network.repository.confchat

import br.com.confchat.mobile.data.network.dto.confchat.ChatSendDto
import br.com.confchat.mobile.data.network.response.confchat.ContactApi
import br.com.confchat.mobile.data.network.response.confchat.MessageApi
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi
import br.com.confchat.mobile.data.network.service.ApiConfchatService

class ChatApiRepository constructor(private val api: ApiConfchatService) : IChatApiRepository {
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

    override fun listContact(page: Int): List<ContactApi> {
        val call = api.listContacts(page = page)
        try {
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!!
            }
            return response.body()!!
        }
        catch (e : Exception){
            return emptyList()
        }
    }

    override fun listMessage(chatId: Int, page: Int): List<MessageApi> {
        val call = api.listMessage(chatId,page)
        try {
            val response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return response.body()!!
        }
        catch (e:Exception){
            return emptyList()
        }
    }

    override fun sendSolict(it: String): ResponseApi<String> {
        val call = api.sendSolicitation(it)
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
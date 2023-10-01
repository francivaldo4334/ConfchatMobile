package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.response.ContactApi
import br.com.confchat.mobile.data.network.response.MessageApi
import br.com.confchat.mobile.data.network.response.ResponseApi
import retrofit2.Call
import retrofit2.http.Query

interface IChatApiRepository {
    public fun send(it: ChatSendDto): ResponseApi<String>
    public fun listContact(page:Int): List<ContactApi>
    public fun listMessage(chatId:Int,page:Int = 0): List<MessageApi>
    public fun sendSolict(it:String):ResponseApi<String>
}
package br.com.confchat.mobile.data.network.repository.confchat

import br.com.confchat.mobile.data.network.dto.confchat.ChatSendDto
import br.com.confchat.mobile.data.network.response.confchat.ContactApi
import br.com.confchat.mobile.data.network.response.confchat.MessageApi
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi

interface IChatApiRepository {
    public fun send(it: ChatSendDto): ResponseApi<String>
    public fun listContact(page:Int): List<ContactApi>
    public fun listMessage(chatId:Int,page:Int = 0): List<MessageApi>
    public fun sendSolict(it:String): ResponseApi<String>
}
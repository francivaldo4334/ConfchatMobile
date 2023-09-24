package br.com.confchat.mobile.domain

import android.util.Log
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.repository.IChatApiRepository
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message

class ChatDomainRepository constructor(private val chat:IChatApiRepository) : IChatDomainRepository {
    override fun listContact(): List<ContactViewModel> {
        val response = chat.listContact(MyConstants.contactPage)
        return response.map { it.toViewModel() }
    }

    override fun listMessage(chatId:Int): List<Message> {
        val response = chat.listMessage(chatId,MyConstants.messagePage)
        return response.sortedBy { it.createAt }.map { it.toViewModel() }
    }

    override fun sendMessage(contactId: String, message: String) {
        val response = chat.send(ChatSendDto(
            fromUserId = contactId,
            message= message,
            "",
            ""
        ))
        if(response.status != 200)
            Log.e(this::class.java.simpleName,response.content)
    }
}
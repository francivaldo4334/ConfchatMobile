package br.com.confchat.mobile.domain

import br.com.confchat.mobile.common.MyConstants
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
        return response.map { it.toViewModel() }
    }
}
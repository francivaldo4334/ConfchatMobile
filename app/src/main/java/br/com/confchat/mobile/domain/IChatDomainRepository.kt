package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message

interface IChatDomainRepository {
    fun listContact():List<ContactViewModel>
    fun listMessage(chatId:Int): List<Message>
    fun sendMessage(contactId:String,message:String)
}
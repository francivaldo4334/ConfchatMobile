package br.com.confchat.mobile.domain.repository.contract

import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message

interface IChatDomainRepository {
    fun listContact():List<ContactViewModel>
    fun listMessage(chatId:Int): List<Message>
    fun sendMessage(contactId:String,message:String)
    fun sendSolicit(it:String)
}
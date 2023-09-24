package br.com.confchat.mobile.domain

import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.repository.IChatApiRepository
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel

class ChatDomainRepository constructor(private val chat:IChatApiRepository) : IChatDomainRepository {
    override fun listContact(): List<ContactViewModel> {
        var response = chat.listContact(MyConstants.contactPage)
        return response.map { it.toViewModel() }
    }
}
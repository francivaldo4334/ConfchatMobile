package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.ContactViewModel

interface IChatDomainRepository {
    fun listContact():List<ContactViewModel>
}
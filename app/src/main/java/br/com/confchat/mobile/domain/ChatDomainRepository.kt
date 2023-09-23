package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.network.repository.IChatApiRepository

class ChatDomainRepository constructor(private val chat:IChatApiRepository) : IChatDomainRepository {
}
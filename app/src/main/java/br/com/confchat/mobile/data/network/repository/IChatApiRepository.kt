package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.response.ContactApi
import br.com.confchat.mobile.data.network.response.ResponseApi

interface IChatApiRepository {
    public fun send(it: ChatSendDto): ResponseApi<String>
    public fun listContact(page:Int): List<ContactApi>
}
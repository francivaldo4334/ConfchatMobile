package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.response.MessageApi
import br.com.confchat.mobile.veiwmodel.model.Message

fun MessageApi.toViewModel():Message{
    return Message(
        fromUserId = this.userId,
        message = this.content
    )
}
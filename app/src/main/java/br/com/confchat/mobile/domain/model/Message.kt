package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.response.confchat.MessageApi
import br.com.confchat.mobile.presenter.veiwmodel.model.Message

fun MessageApi.toViewModel():Message{
    return Message(
        fromUserId = this.userId,
        message = this.content
    )
}
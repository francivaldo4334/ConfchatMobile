package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.response.ContactApi
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel

fun ContactApi.toViewModel():ContactViewModel{
    return ContactViewModel(
        chatId = this.chatId,
        id = this.userId,
        urlImg = this.urlImage?:"",
        name = this.name,
        previewMessage = this.previewMessage,
        coutNewsMessage = MyConstants.newMessages.count{it.first.equals(this.userId)},
        isEncripted = this.encripted,
        isOnline = false
    )
}
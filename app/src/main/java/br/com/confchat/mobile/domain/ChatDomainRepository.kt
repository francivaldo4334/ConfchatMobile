package br.com.confchat.mobile.domain

import android.util.Log
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.repository.IChatApiRepository
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.domain.services.WebSocketService
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message
import com.google.gson.Gson
import okhttp3.Headers
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.WebSocket

class ChatDomainRepository constructor(private val chat:IChatApiRepository) : IChatDomainRepository {
    override fun listContact(): List<ContactViewModel> {
        val response = chat.listContact(MyConstants.contactPage)
        return response.map { it.toViewModel() }
    }

    override fun listMessage(chatId:Int): List<Message> {
        val response = chat.listMessage(chatId,MyConstants.messagePage)
        return response.sortedBy { it.createAt }.map { it.toViewModel() }
    }

    override fun sendMessage(contactId: String, message: String) {
        val dto = ChatSendDto(
            fromUserId = contactId,
            message= message,
            "",
            ""
        )
        val response = chat.send(dto)
        if(response.status != 200) {
            Log.e(this::class.java.simpleName, response.content)

//            val client = OkHttpClient()
//            val request : Request = Request
//                .Builder()
//                .put(Gson().toJson(dto).toRequestBody())
//                .headers(Headers.headersOf("Authorization: Bearer ${MyConstants.TOKEN}"))
//                .url("ws://192.168.0.9:8080/ws/connect")
//                .build()
//            val listener = WebSocketService()
//            val ws : WebSocket = client.newWebSocket(request,listener)
        }
    }

    override fun sendSolicit(it: String) {
        val response = chat.sendSolict(it)
    }
}
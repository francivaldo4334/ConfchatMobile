package br.com.confchat.mobile.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.domain.IChatDomainRepository
import br.com.confchat.mobile.domain.services.WebSocketService
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.WebSocket
import javax.inject.Inject

@HiltViewModel
class ChatViewModel
@Inject
constructor(
    private val chat:IChatDomainRepository
):ViewModel(){
    private val loadListContact = MutableStateFlow<List<ContactViewModel>>(emptyList())
    val listContact = loadListContact
    private val loadListMessage = MutableStateFlow<List<Message>>(emptyList())
    val listMessage = loadListMessage
    fun loadContacts(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = chat.listContact()
            loadListContact.update {list}
        }
    }

    fun loadMessages(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = chat.listMessage(id)
            loadListMessage.update {list}
        }
    }

    fun sendMessage(id: String, message: String) {
        viewModelScope.launch(Dispatchers.IO) {
            chat.sendMessage(id,message)
        }
    }
}
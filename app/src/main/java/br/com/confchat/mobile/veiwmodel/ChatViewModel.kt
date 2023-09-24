package br.com.confchat.mobile.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.IChatDomainRepository
import br.com.confchat.mobile.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.veiwmodel.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel
@Inject
constructor(
    private val chat:IChatDomainRepository
):ViewModel(){
    val listContact = MutableStateFlow<List<ContactViewModel>>(emptyList())
    val listMessage = MutableStateFlow<List<Message>>(emptyList())
    fun loadContacts(){
        viewModelScope.launch(Dispatchers.IO) {
            val list = chat.listContact()
            listContact.update {list}
        }
    }

    fun loadMessages(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = chat.listMessage(id)
            listMessage.update { list }
        }
    }
}
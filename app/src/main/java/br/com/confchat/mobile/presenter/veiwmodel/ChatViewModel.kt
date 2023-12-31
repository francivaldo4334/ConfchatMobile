package br.com.confchat.mobile.presenter.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.repository.contract.IChatDomainRepository
import br.com.confchat.mobile.presenter.veiwmodel.model.ContactViewModel
import br.com.confchat.mobile.presenter.veiwmodel.model.Message
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
    private val chat: IChatDomainRepository
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
    fun sendSolicit(it:String){
        viewModelScope.launch(Dispatchers.IO) {
            chat.sendSolicit(it)
        }
    }
}
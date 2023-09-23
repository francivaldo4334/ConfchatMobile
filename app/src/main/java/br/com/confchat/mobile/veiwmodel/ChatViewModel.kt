package br.com.confchat.mobile.veiwmodel

import androidx.lifecycle.ViewModel
import br.com.confchat.mobile.domain.IChatDomainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel
@Inject
constructor(
    chat:IChatDomainRepository
):ViewModel(){

}
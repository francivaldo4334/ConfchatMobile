package br.com.confchat.mobile.veiwmodel

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.AuthDomainRepository
import br.com.confchat.mobile.domain.IAuthDomainRepository
import br.com.confchat.mobile.view.constants.AuthDoc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth:IAuthDomainRepository): ViewModel(){
    fun login(function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var response = auth.login(AuthDoc.login)
            viewModelScope.launch(Dispatchers.Main){
                function.invoke(response.first)
            }
        }
    }

    fun register(function: (Boolean,String) -> Unit) {

    }

    fun checkLogin() : Boolean {
        var response = auth.CheckLogin()
        return response;
    }

}
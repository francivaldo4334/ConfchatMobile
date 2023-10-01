package br.com.confchat.mobile.veiwmodel

import android.provider.SyncStateContract.Constants
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.AuthDomainRepository
import br.com.confchat.mobile.domain.IAuthDomainRepository
import br.com.confchat.mobile.view.constants.AuthDoc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
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
        viewModelScope.launch(Dispatchers.IO) {
            val response = auth.register(AuthDoc.register)
            viewModelScope.launch(Dispatchers.Main) {
                function(response.first,response.second)
            }
        }
    }

    fun checkLogin(function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var response = auth.CheckLogin()
            viewModelScope.launch(Dispatchers.Main) {
                function.invoke(response)
            }
        }
    }

    fun checkVerificationCode(code: String,function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = auth.checkVerificationCode(code)
            viewModelScope.launch(Dispatchers.Main) {
                function(response)
            }
        }
    }

    fun logout() {
        auth.logout()
    }

    fun resendVerificationCode(function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = auth.resendVerificationCode(AuthDoc.register.email)
            viewModelScope.launch(Dispatchers.Main) {
                function(response)
            }
        }
    }

}
package br.com.confchat.mobile.presenter.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.repository.contract.IAuthDomainRepository
import br.com.confchat.mobile.presenter.view.constants.AuthDoc
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val auth: IAuthDomainRepository): ViewModel(){
    fun login(AuthDoc:AuthDoc,function: (Boolean,String) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var response = auth.login(AuthDoc.login)
            viewModelScope.launch(Dispatchers.Main){
                function.invoke(response.first,"Usuario ou senha incorreto.")
            }
        }
    }

    fun register(AuthDoc:AuthDoc,function: (Boolean,String) -> Unit) {
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

    fun checkVerificationCode(AuthDoc:AuthDoc,code: String,function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = auth.checkVerificationCode(code,AuthDoc)
            viewModelScope.launch(Dispatchers.Main) {
                function(response)
            }
        }
    }

    fun logout() {
        auth.logout()
    }

    fun resendVerificationCode(AuthDoc:AuthDoc,function: (Boolean) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = auth.resendVerificationCode(AuthDoc.register.email)
            viewModelScope.launch(Dispatchers.Main) {
                function(response)
            }
        }
    }

    fun sendRequestPassword(email: String,function:(Boolean)->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            var result = auth.sendRequestPassword(email)
            viewModelScope.launch(Dispatchers.Main) {
                function(result)
            }
        }

    }

    fun RequestPassword(code: String, authDoc: AuthDoc,function:(Boolean)->Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = auth.resetPassword(authDoc.resetPassword)
            viewModelScope.launch(Dispatchers.Main) {
                function(result)
            }
        }
    }

}
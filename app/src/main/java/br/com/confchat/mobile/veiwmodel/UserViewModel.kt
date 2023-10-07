package br.com.confchat.mobile.veiwmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.repository.contract.IUserDomainRepository
import br.com.confchat.mobile.veiwmodel.model.Device
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel
@Inject
constructor(
    private val user : IUserDomainRepository
) : ViewModel(){
    var listDevice = MutableStateFlow(emptyList<Device>())
    fun getListDevice() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val list = user.getListDevice()
                listDevice.update {
                    list
                }

            } catch (e: Exception) {
                Log.e(this::class.simpleName,e.message.toString())
            }
        }
    }

    fun loadData() {

    }
}
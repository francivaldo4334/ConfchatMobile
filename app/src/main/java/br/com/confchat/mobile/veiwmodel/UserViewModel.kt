package br.com.confchat.mobile.veiwmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.data.network.response.DeviceApi
import br.com.confchat.mobile.domain.IUserDomainRepository
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.veiwmodel.model.Device
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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
        //user.getListDevice()
//        .flatMapConcat {
//            var vm = it.map { it.toViewModel() }
//            MutableStateFlow(vm)
//        }
//    .stateIn( viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
}
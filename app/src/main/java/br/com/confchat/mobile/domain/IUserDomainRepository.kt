package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.network.response.DeviceApi
import br.com.confchat.mobile.veiwmodel.model.Device
import kotlinx.coroutines.flow.Flow

interface IUserDomainRepository {
    fun getListDevice(): List<Device>
}
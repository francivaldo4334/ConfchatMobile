package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.response.DeviceApi
import kotlinx.coroutines.flow.Flow

interface IUserApiRepository {
    abstract fun getListDevice(): List<DeviceApi>
}
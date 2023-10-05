package br.com.confchat.mobile.data.network.repository.confchat

import br.com.confchat.mobile.data.network.response.confchat.DeviceApi
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi

interface IUserApiRepository {
    abstract fun getListDevice(): List<DeviceApi>
    abstract fun getMe(): ResponseApi<String>
}
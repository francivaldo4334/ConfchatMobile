package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.network.response.confchat.DeviceApi
import br.com.confchat.mobile.veiwmodel.model.Device

fun DeviceApi.toViewModel(): Device{
    return Device(
        id = this.id,
        name = this.name,
        thisDevice = this.thisDevice
    )
}
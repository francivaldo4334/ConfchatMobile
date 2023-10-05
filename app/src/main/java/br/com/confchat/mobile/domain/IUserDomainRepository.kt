package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.Device

interface IUserDomainRepository {
    fun getListDevice(): List<Device>
}
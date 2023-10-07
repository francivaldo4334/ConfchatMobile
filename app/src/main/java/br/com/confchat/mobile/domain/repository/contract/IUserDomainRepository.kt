package br.com.confchat.mobile.domain.repository.contract

import br.com.confchat.mobile.veiwmodel.model.Device

interface IUserDomainRepository {
    fun getListDevice(): List<Device>
}
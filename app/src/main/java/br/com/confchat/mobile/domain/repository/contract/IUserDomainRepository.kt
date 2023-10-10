package br.com.confchat.mobile.domain.repository.contract

import br.com.confchat.mobile.presenter.veiwmodel.model.Device

interface IUserDomainRepository {
    fun getListDevice(): List<Device>
}
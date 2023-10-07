package br.com.confchat.mobile.domain.repository.implementation

import br.com.confchat.mobile.data.network.repository.confchat.IUserApiRepository
import br.com.confchat.mobile.data.network.response.confchat.DeviceApi
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.domain.repository.contract.IUserDomainRepository
import br.com.confchat.mobile.veiwmodel.model.Device

class UserDomainRepository constructor(private val user : IUserApiRepository) :
    IUserDomainRepository {
    override fun getListDevice(): List<Device> {
        var response : List<DeviceApi> = user.getListDevice()
        return response.map { it.toViewModel() };
    }
}
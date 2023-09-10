package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.network.repository.IAuthApiRepository
import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.toDto
import javax.inject.Inject

class AuthDomainRepository @Inject constructor(
    private val auth: IAuthApiRepository
) : IAuthDomainRepository {
    override fun login(login: Login): Pair<Boolean, String> {
        var response = auth.Login(login.toDto())
        return Pair(response.status.equals(200).and(response.content.isNotEmpty()),response.content);
    }

    override fun register(function: (Boolean,String) -> Unit) {

    }
}
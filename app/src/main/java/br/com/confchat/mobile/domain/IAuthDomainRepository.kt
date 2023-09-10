package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.Login

interface IAuthDomainRepository {
    fun login(login: Login):Pair<Boolean,String>;
    fun register(function: (Boolean,String) -> Unit);
}
package br.com.confchat.mobile.domain.repository.contract

import br.com.confchat.mobile.presenter.veiwmodel.model.Login
import br.com.confchat.mobile.presenter.veiwmodel.model.Register
import br.com.confchat.mobile.presenter.view.constants.AuthDoc

interface IAuthDomainRepository {
    fun login(login: Login):Pair<Boolean,String>;
    fun register(it:Register) : Pair<Boolean,String>;
    abstract fun CheckLogin(): Boolean
    abstract fun updateToken(tokenUpdate:String):Boolean
    fun checkVerificationCode(code:String,AuthDoc: AuthDoc): Boolean
    fun logout()
    fun resendVerificationCode(it:String): Boolean
}
package br.com.confchat.mobile.domain

import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.Register

interface IAuthDomainRepository {
    fun login(login: Login):Pair<Boolean,String>;
    fun register(it:Register) : Pair<Boolean,String>;
    abstract fun CheckLogin(): Boolean
    abstract fun updateToken(tokenUpdate:String):Boolean
    fun checkVerificationCode(code:String): Boolean
    fun logout()
    fun resendVerificationCode(it:String): Boolean
}
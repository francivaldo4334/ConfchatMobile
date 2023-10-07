package br.com.confchat.mobile.domain.repository.implementation

import android.content.Context
import android.os.Build
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.dto.confchat.CheckVerificationCodeDto
import br.com.confchat.mobile.data.network.repository.confchat.IAuthApiRepository
import br.com.confchat.mobile.data.network.repository.confchat.IUserApiRepository
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi
import br.com.confchat.mobile.domain.model.toDto
import br.com.confchat.mobile.domain.repository.contract.IAuthDomainRepository
import br.com.confchat.mobile.veiwmodel.model.Login
import br.com.confchat.mobile.veiwmodel.model.Register
import br.com.confchat.mobile.view.constants.AuthDoc
import java.util.Calendar
import java.util.Date

class AuthDomainRepository constructor(
    private val auth: IAuthApiRepository,
    private val user: IUserApiRepository,
    private val context : Context?
) : IAuthDomainRepository {
    val shared = context?.getSharedPreferences(MyConstants.AUTENTICATION_DATA,Context.MODE_PRIVATE)
    val sharedEdit = shared?.edit()
    override fun login(login: Login): Pair<Boolean, String> {
        val deviceName = getDeviceName()
        login.deviceName = deviceName
        var response : ResponseApi<String> = auth.Login(login.toDto())
        if(response.content.isNotEmpty() && response.status == 200){
            saveTokens(response)
        }

        return Pair(response.status.equals(200).and(response.content.isNotEmpty()),response.content);
    }
    private fun saveTokens(response: ResponseApi<String>):Boolean{
        var lsToken = response.content.split(";")
        if(lsToken.size < 2)
            return false
        MyConstants.TOKEN = lsToken[0]
        sharedEdit?.putString(MyConstants.TOKEN_LOGIN_DATA,lsToken[0])
        if(lsToken.size > 1){
            MyConstants.TOKEN_UPDATE = lsToken[1]
            sharedEdit?.putString(MyConstants.TOKEN_UPDATE_DATA, lsToken[1])
        }
        sharedEdit?.putLong(MyConstants.LOGIN_AT,Date().time)
        sharedEdit?.apply()
        return true
    }
    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        return if (model.startsWith(manufacturer)) {
            model
        } else {
            "$manufacturer $model"
        }
    }
    override fun register(it:Register) : Pair<Boolean,String> {
        var response = auth.Register(it.toDto())
        if(response.status == 200)
            return Pair(true,response.content)
        return Pair(false,response.content)
    }

    override fun CheckLogin(): Boolean {
        MyConstants.TOKEN = shared?.getString(MyConstants.TOKEN_LOGIN_DATA,"")!!
        MyConstants.TOKEN_UPDATE = shared.getString(MyConstants.TOKEN_LOGIN_DATA,"")!!
        if(MyConstants.TOKEN.isNotEmpty()){
            var result = checkTokenValid()
            return result
        }
        return false
    }

    override fun updateToken(tokenUpdate: String):Boolean{
        var result = auth.UpdateToken(tokenUpdate)
        when(result.status){
            200 ->{
                user.getMe()
                saveTokens(result)
                return true
            }
            403 ->{
                return false
            }
            else -> {
                loadCacheUserMe()
                return true
            }
        }
    }

    override fun checkVerificationCode(code:String): Boolean {
        var check = CheckVerificationCodeDto(
            code = code,
            email = AuthDoc.register.email
        )
        val response = auth.checkVerificationCode(check)
        if(response.status == 200)
            return true
        return false
    }

    override fun logout() {
        sharedEdit?.remove(MyConstants.TOKEN_UPDATE_DATA)
        sharedEdit?.remove(MyConstants.TOKEN_LOGIN_DATA)
        sharedEdit?.apply()
    }

    override fun resendVerificationCode(it: String): Boolean {
        var check = auth.resendVerificationCode(it)
        if(check.status == 200)
            return true
        return false
    }

    public fun checkDateUpdateToken(long:Long):Boolean{
        val calendar = Calendar.getInstance()
        calendar.time = Date(long)
        calendar.add(Calendar.DAY_OF_MONTH,25)

        val dateLoginAt = calendar.time
        val dateNow = Date()
        if(dateNow.after(dateLoginAt)){
            return false
        }
        return true
    }
    private fun checkTokenValid():Boolean{
        val longLoginAt = shared?.getLong(MyConstants.LOGIN_AT,0L)?:0L
        if(!checkDateUpdateToken(longLoginAt)){
            var result = updateToken(MyConstants.TOKEN_UPDATE)
            return result
        }
        var result = user.getMe()
        when(result.status){
            200 ->{
                loadCacheUserMe()
                return true
            }
            403 ->{
                var result = updateToken(MyConstants.TOKEN_UPDATE)
                return result
            }
            else ->{
                loadCacheUserMe()
                return true
            }
        }
    }

    private fun loadCacheUserMe(){
        //TODO:Carregar dados de cache
    }
}
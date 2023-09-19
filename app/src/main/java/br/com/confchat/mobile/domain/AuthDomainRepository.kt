package br.com.confchat.mobile.domain

import android.content.Context
import android.os.Build
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.repository.IAuthApiRepository
import br.com.confchat.mobile.data.network.repository.IUserApiRepository
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.domain.model.toDto
import br.com.confchat.mobile.veiwmodel.model.Login

class AuthDomainRepository constructor(
    private val auth: IAuthApiRepository,
    private val user: IUserApiRepository,
    private val context : Context
) : IAuthDomainRepository {
    val shared = context.getSharedPreferences(MyConstants.AUTENTICATION_DATA,Context.MODE_PRIVATE)
    val sharedEdit = shared.edit()
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
        MyConstants.TOKEN_UPDATE = lsToken[1]
        sharedEdit.putString(MyConstants.TOKEN_LOGIN_DATA,lsToken[0])
        sharedEdit.putString(MyConstants.TOKEN_UPDATE_DATA,lsToken[1])
        sharedEdit.apply()
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
    override fun register(function: (Boolean,String) -> Unit) {

    }

    override fun CheckLogin(): Boolean {
        MyConstants.TOKEN = shared.getString(MyConstants.TOKEN_LOGIN_DATA,"")!!
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

    private fun checkTokenValid():Boolean{
        var result = user.getMe()
        when(result.status){
            200 ->{
                //TODO:Save data and load cache
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
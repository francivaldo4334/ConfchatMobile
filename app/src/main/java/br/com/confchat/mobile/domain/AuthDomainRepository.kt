package br.com.confchat.mobile.domain

import android.content.Context
import android.os.Build
import br.com.confchat.mobile.common.MyConstants
import br.com.confchat.mobile.data.network.repository.IAuthApiRepository
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.domain.model.toDto
import br.com.confchat.mobile.veiwmodel.model.Login
import javax.inject.Inject

class AuthDomainRepository @Inject constructor(
    private val auth: IAuthApiRepository,
    private val context : Context
) : IAuthDomainRepository {
    val shared = context.getSharedPreferences(MyConstants.AUTENTICATION_DATA,Context.MODE_PRIVATE)
    val sharedEdit = shared.edit()
    override fun login(login: Login): Pair<Boolean, String> {
        val deviceName = getDeviceName()
        login.deviceName = deviceName
        var response : ResponseApi<String> = auth.Login(login.toDto())
        if(response.content.isNotEmpty() && response.status == 200){
            var lsToken = response.content.split(";")
            sharedEdit.putString(MyConstants.TOKEN_LOGIN_DATA,lsToken[0])
            sharedEdit.putString(MyConstants.TOKEN_UPDATE_DATA,lsToken[1])
            sharedEdit.apply()
        }

        return Pair(response.status.equals(200).and(response.content.isNotEmpty()),response.content);
    }
    fun getDeviceName(): String {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL

        return if (model.startsWith(manufacturer)) {
            // If the model starts with the manufacturer, return the model
            model
        } else {
            // Otherwise, return both the manufacturer and model
            "$manufacturer $model"
        }
    }
    override fun register(function: (Boolean,String) -> Unit) {

    }

    override fun CheckLogin(): Boolean {
        var token = shared.getString(MyConstants.TOKEN_LOGIN_DATA,null)
        if(token != null){
            MyConstants.TOKEN = token;
            return true
        }
        return false
    }
}
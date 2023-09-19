package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.service.ApiConfchatService
import br.com.confchat.mobile.data.network.response.UpdateToken
class AuthApiRepository constructor(private val api: ApiConfchatService): IAuthApiRepository {
    override fun Login(it: LoginDto): ResponseApi<String> {
        val call = api.Login(it)
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                return apiResponse!!
            } else {
                return response.body()!!
            }
        } catch (e: Exception) {
            return ResponseApi<String>("Error: connection fail ${e}", 503);
        }
    }

    override fun Register(it: RegisterDto): ResponseApi<String> {
        TODO("Not yet implemented")
    }

    override fun UpdateToken(it: String): ResponseApi<String> {
        var call = api.updateLogin(UpdateToken(
            updateToken = it
        ))
        try {
            var response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return ResponseApi("",403)
        }
        catch (e:Exception){
            return ResponseApi(e.message.toString(),503)
        }
    }
}
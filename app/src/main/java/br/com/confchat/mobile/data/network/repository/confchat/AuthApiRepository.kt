package br.com.confchat.mobile.data.network.repository.confchat

import br.com.confchat.mobile.data.network.dto.confchat.CheckVerificationCodeDto
import br.com.confchat.mobile.data.network.dto.confchat.LoginDto
import br.com.confchat.mobile.data.network.dto.confchat.RegisterDto
import br.com.confchat.mobile.data.network.dto.confchat.ResetPasswordDto
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi
import br.com.confchat.mobile.data.network.response.confchat.ResponseApiString
import br.com.confchat.mobile.data.network.service.ApiConfchatService
import br.com.confchat.mobile.data.network.response.confchat.UpdateToken
import com.google.gson.Gson

class AuthApiRepository constructor(private val api: ApiConfchatService): IAuthApiRepository {
    override fun Login(it: LoginDto): ResponseApi<String> {
        val call = api.Login(it)
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                return apiResponse!!
            } else {
                return Gson().fromJson(response.errorBody()!!.string(), ResponseApiString::class.java)
            }
        } catch (e: Exception) {
            return ResponseApi<String>("Error: connection fail ${e}", 503);
        }
    }

    override fun Register(it: RegisterDto): ResponseApi<String> {
        val call = api.Register(it)
        try {
            var response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return Gson().fromJson(response.errorBody()!!.string(), ResponseApiString::class.java)
        }
        catch (e:Exception){
            return ResponseApi("Error",503)
        }
    }

    override fun UpdateToken(it: String): ResponseApi<String> {
        var call = api.updateLogin(
            UpdateToken(
            updateToken = it
        )
        )
        try {
            var response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return Gson().fromJson(response.errorBody()!!.string(), ResponseApiString::class.java)
        }
        catch (e:Exception){
            return ResponseApi(e.message.toString(),503)
        }
    }

    override fun checkVerificationCode(it: CheckVerificationCodeDto): ResponseApi<String> {
        val call = api.checkVerificationCode(it)
        try {
            val response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return Gson().fromJson(response.errorBody()!!.string(), ResponseApiString::class.java)
        }
        catch (e : Exception){
            return ResponseApi("Error",503)
        }
    }

    override fun resendVerificationCode(it: String): ResponseApi<String> {
        val call = api.resendVerificationCode(it)
        try {
            val response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return Gson().fromJson(response.errorBody()!!.string(), ResponseApiString::class.java)
        }
        catch (e : Exception){
            return ResponseApi("Error",503)
        }
    }

    override fun sendRequestPassword(it: String): ResponseApi<String> {
        val call = api.sendRequestPassword(it)
        try {
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!!
            }
            return Gson().fromJson(response.errorBody()!!.string(),ResponseApiString::class.java)
        }
        catch (e:Exception){
            return ResponseApi("Verifique sua conecao com a internet",501)
        }
    }

    override fun resetPassword(it: ResetPasswordDto): ResponseApi<String> {
        val call = api.resetPassword(it)
        try {
            val response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return Gson().fromJson(response.errorBody()!!.string(),ResponseApiString::class.java)
        }
        catch (e:Exception){
            return ResponseApi("Verifique sua conecao com a internet",501)
        }
    }
}
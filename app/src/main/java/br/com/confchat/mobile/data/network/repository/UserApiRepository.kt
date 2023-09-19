package br.com.confchat.mobile.data.network.repository

import android.util.Log
import br.com.confchat.mobile.data.network.response.DeviceApi
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.service.ApiConfchatService
import kotlinx.coroutines.flow.Flow

class UserApiRepository constructor(private val user : ApiConfchatService) : IUserApiRepository {
    override fun getListDevice(): List<DeviceApi> {
        val call = user.getListDevice()
        try {
            val response = call.execute()
            if (response.isSuccessful) {
                val apiResponse = response.body()
                return apiResponse!!
            } else {
                return response.body()!!
            }
        } catch (e: Exception) {
            Log.e(UserApiRepository::class.simpleName,e.message.toString())
            return emptyList()
        }
    }

    override fun getMe(): ResponseApi<String> {
        val call = user.getMe()
        try {
            val response = call.execute()
            if(response.isSuccessful)
                return response.body()!!
            return ResponseApi("",403)
        }
        catch (e:Exception){
            return ResponseApi(e.message.toString(),503)
        }
    }
}
package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.service.ApiConfchatService

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
}
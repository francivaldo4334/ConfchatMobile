package br.com.confchat.mobile.data.network.service

import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.ResponseApi
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiConfchatService {
    @POST("auth/login")
    fun Login(@Body it: LoginDto):Call<ResponseApi<String>>
    @POST("auth/register")
    fun Register(@Body it:RegisterDto):Call<ResponseApi<String>>
}
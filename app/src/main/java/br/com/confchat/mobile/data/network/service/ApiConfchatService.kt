package br.com.confchat.mobile.data.network.service

import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.DeviceApi
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.response.UpdateToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiConfchatService {
    @POST("auth/login")
    fun Login(@Body it: LoginDto):Call<ResponseApi<String>>
    @POST("auth/register")
    fun Register(@Body it:RegisterDto):Call<ResponseApi<String>>
    @POST("auth/update-login")
    fun updateLogin(@Body it: UpdateToken):Call<ResponseApi<String>>
    @GET("user/loged-devices")
    fun getListDevice(): Call<List<DeviceApi>>
    @GET("user/me")
    fun getMe():Call<ResponseApi<String>>
}
package br.com.confchat.mobile.data.network.service

import br.com.confchat.mobile.data.network.dto.ChatSendDto
import br.com.confchat.mobile.data.network.dto.CheckVerificationCodeDto
import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.ContactApi
import br.com.confchat.mobile.data.network.response.DeviceApi
import br.com.confchat.mobile.data.network.response.MessageApi
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.response.UpdateToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiConfchatService {
    @POST("auth/login")
    fun Login(@Body it: LoginDto):Call<ResponseApi<String>>
    @POST("auth/register")
    fun Register(@Body it:RegisterDto):Call<ResponseApi<String>>
    @POST("auth/update-login")
    fun updateLogin(@Body it: UpdateToken):Call<ResponseApi<String>>
    @PUT("auth/check-verification-code")
    fun checkVerificationCode(@Body it:CheckVerificationCodeDto):Call<ResponseApi<String>>
    @GET("user/loged-devices")
    fun getListDevice(): Call<List<DeviceApi>>
    @GET("user/me")
    fun getMe():Call<ResponseApi<String>>
    @POST("chat/send")
    fun send(@Body it:ChatSendDto):Call<ResponseApi<String>>
    @GET("chat/list-contact")
    fun listContacts(@Query("page") page:Int = 0,@Query("size") size: Int = 10):Call<List<ContactApi>>
    @GET("chat/message-by-chat")
    fun listMessage(@Query("chatId") chatId:Int,@Query("page") page:Int = 0,@Query("size") size:Int = 10):Call<List<MessageApi>>
}
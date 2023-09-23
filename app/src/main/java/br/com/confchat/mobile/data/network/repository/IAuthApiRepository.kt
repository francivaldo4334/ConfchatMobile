package br.com.confchat.mobile.data.network.repository

import br.com.confchat.mobile.data.network.dto.CheckVerificationCodeDto
import br.com.confchat.mobile.data.network.dto.LoginDto
import br.com.confchat.mobile.data.network.dto.RegisterDto
import br.com.confchat.mobile.data.network.response.ResponseApi
import br.com.confchat.mobile.data.network.response.UpdateToken
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface IAuthApiRepository {
    fun Login(it: LoginDto): ResponseApi<String>
    fun Register(it: RegisterDto): ResponseApi<String>
    fun UpdateToken(it:String): ResponseApi<String>
    fun checkVerificationCode(it:CheckVerificationCodeDto): ResponseApi<String>
}
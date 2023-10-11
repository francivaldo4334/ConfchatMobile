package br.com.confchat.mobile.data.network.repository.confchat

import br.com.confchat.mobile.data.network.dto.confchat.CheckVerificationCodeDto
import br.com.confchat.mobile.data.network.dto.confchat.LoginDto
import br.com.confchat.mobile.data.network.dto.confchat.RegisterDto
import br.com.confchat.mobile.data.network.dto.confchat.ResetPasswordDto
import br.com.confchat.mobile.data.network.response.confchat.ResponseApi

interface IAuthApiRepository {
    fun Login(it: LoginDto): ResponseApi<String>
    fun Register(it: RegisterDto): ResponseApi<String>
    fun UpdateToken(it:String): ResponseApi<String>
    fun checkVerificationCode(it: CheckVerificationCodeDto): ResponseApi<String>
    fun resendVerificationCode(it:String): ResponseApi<String>
    fun sendRequestPassword(it:String):ResponseApi<String>
    fun resetPassword(it:ResetPasswordDto):ResponseApi<String>
}
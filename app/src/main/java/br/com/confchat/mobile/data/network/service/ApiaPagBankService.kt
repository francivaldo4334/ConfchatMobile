package br.com.confchat.mobile.data.network.service

import br.com.confchat.mobile.data.network.dto.pagbank.CheckOrderResponse
import br.com.confchat.mobile.data.network.dto.pagbank.CreateOrderDto
import br.com.confchat.mobile.data.network.response.pagbank.CreateOrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiaPagBankService {
    @POST("orders")
    fun createOrder(@Body it:CreateOrderDto): Call<CreateOrderResponse>
    @GET("orders/{order_id}")
    fun checkOrder(@Path(value = "order_id") it:String): Call<CheckOrderResponse>
}
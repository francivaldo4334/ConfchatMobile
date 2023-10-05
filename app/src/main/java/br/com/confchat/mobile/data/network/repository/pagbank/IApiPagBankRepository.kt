package br.com.confchat.mobile.data.network.repository.pagbank

import br.com.confchat.mobile.data.network.dto.pagbank.CheckOrderResponse
import br.com.confchat.mobile.data.network.dto.pagbank.CreateOrderDto
import br.com.confchat.mobile.data.network.response.pagbank.CreateOrderResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiPagBankRepository {
    fun createOrder( it: CreateOrderDto): CreateOrderResponse?
    fun checkOrder( it:String): CheckOrderResponse?
}
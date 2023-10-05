package br.com.confchat.mobile.data.network.repository.pagbank

import android.util.Log
import br.com.confchat.mobile.data.network.dto.pagbank.CheckOrderResponse
import br.com.confchat.mobile.data.network.dto.pagbank.CreateOrderDto
import br.com.confchat.mobile.data.network.response.pagbank.CreateOrderResponse
import br.com.confchat.mobile.data.network.service.ApiaPagBankService

class ApiPagBankRepository constructor(private val api:ApiaPagBankService) : IApiPagBankRepository {
    override fun createOrder(it: CreateOrderDto): CreateOrderResponse? {
        val call = api.createOrder(it)
        try {
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!!
            }
            return null
        }
        catch (e:Exception){
            Log.e(this@ApiPagBankRepository::class.java.simpleName,e.message.toString())
            return null
        }
    }

    override fun checkOrder(it: String): CheckOrderResponse? {
        val call = api.checkOrder(it)
        try {
            val response = call.execute()
            if(response.isSuccessful){
                return response.body()!!
            }
            return null
        }
        catch (e:Exception){
            Log.e(this@ApiPagBankRepository::class.java.simpleName,e.message.toString())
            return null
        }
    }
}
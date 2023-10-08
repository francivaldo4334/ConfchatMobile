package br.com.confchat.mobile.data.database.repository.contract

import androidx.lifecycle.LiveData
import br.com.confchat.mobile.data.database.entitys.Product
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun creatProduct(product: Product)
    fun getAll(): Flow<List<Product>>
}
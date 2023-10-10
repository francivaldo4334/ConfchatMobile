package br.com.confchat.mobile.data.database.repository.implementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.confchat.mobile.data.database.dao.ProductDao
import br.com.confchat.mobile.data.database.entitys.Product
import br.com.confchat.mobile.data.database.repository.contract.IProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ProductRepository constructor(private val dao:ProductDao) : IProductRepository {
    override fun creatProduct(product: Product) {
        dao.insert(product)
    }

    override fun getAll(): Flow<List<Product>> {
        return dao.getAll()
    }

    override fun delete(id: Int) {
        dao.delete(id)
    }

    override fun update(toModel: Product) {
        dao.update(toModel)
    }
}
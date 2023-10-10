package br.com.confchat.mobile.domain.repository.implementation

import androidx.lifecycle.LiveData
import br.com.confchat.mobile.data.database.entitys.Product
import br.com.confchat.mobile.data.database.repository.contract.IProductRepository
import br.com.confchat.mobile.domain.model.toModel
import br.com.confchat.mobile.domain.model.toViewModel
import br.com.confchat.mobile.domain.repository.contract.IProductDomainRepository
import br.com.confchat.mobile.veiwmodel.model.ProductModeltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest

class ProductDomainRepository constructor(private val doc:IProductRepository) : IProductDomainRepository {
    override fun createProduct(it: ProductModeltViewModel) {
        doc.creatProduct(Product(name = it.name, price = it.value))
    }

    override fun getAll(): Flow<List<ProductModeltViewModel>> {
        return doc.getAll().flatMapLatest {
            MutableStateFlow(it.map { it.toViewModel() })
        }
    }

    override fun delete(product: ProductModeltViewModel) {
        doc.delete(product.id)
    }

    override fun update(product: ProductModeltViewModel) {
        doc.update(product.toModel())
    }
}
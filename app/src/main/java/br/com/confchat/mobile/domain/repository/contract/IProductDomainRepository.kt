package br.com.confchat.mobile.domain.repository.contract

import androidx.lifecycle.LiveData
import br.com.confchat.mobile.presenter.veiwmodel.model.ProductModeltViewModel
import kotlinx.coroutines.flow.Flow

interface IProductDomainRepository {
    abstract fun createProduct(it: ProductModeltViewModel)
    abstract fun getAll():Flow<List<ProductModeltViewModel>>
    fun delete(product: ProductModeltViewModel)
    fun update(product: ProductModeltViewModel)
}
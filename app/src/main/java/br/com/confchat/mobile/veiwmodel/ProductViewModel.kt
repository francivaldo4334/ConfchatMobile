package br.com.confchat.mobile.veiwmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.confchat.mobile.domain.repository.contract.IProductDomainRepository
import br.com.confchat.mobile.veiwmodel.model.ProductModeltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingCommand
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val domain:IProductDomainRepository) : ViewModel() {
    val listProduct: StateFlow<List<ProductModeltViewModel>> = domain.getAll().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )
    fun creatProduct(it: ProductModeltViewModel){
        domain.createProduct(it)
    }

    fun delete(product: ProductModeltViewModel) {
        domain.delete(product)
    }

    fun update(product: ProductModeltViewModel) {
        domain.update(product)
    }
}
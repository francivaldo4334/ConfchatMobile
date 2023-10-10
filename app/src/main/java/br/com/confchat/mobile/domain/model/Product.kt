package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.database.entitys.Product
import br.com.confchat.mobile.presenter.veiwmodel.model.ProductModeltViewModel

fun Product.toViewModel(): ProductModeltViewModel{
    return ProductModeltViewModel(
        name = this.name,
        value = this.price,
        img = "",
        id = this.id
    )
}
fun ProductModeltViewModel.toModel():Product{
    return Product(
        id = this.id,
        name = this.name,
        price = this.value
    )
}
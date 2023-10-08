package br.com.confchat.mobile.domain.model

import br.com.confchat.mobile.data.database.entitys.Product
import br.com.confchat.mobile.veiwmodel.model.ProductModeltViewModel

fun Product.toViewModel(): ProductModeltViewModel{
    return ProductModeltViewModel(
        name = this.name,
        value = this.price,
        img = ""
    )
}
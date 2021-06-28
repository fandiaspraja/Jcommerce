package com.fandiaspraja.sehatqcommerce.ui.detailproduct

import androidx.lifecycle.ViewModel
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceUseCase

class DetailProductViewModel(private val useCase: EcommerceUseCase): ViewModel() {

    fun setFavoriteProduct(product: Product, newStatus:Boolean) =
        useCase.setProductFavorite(product, newStatus)

    fun setBuyProduct(product: Product, newStatus:Boolean) =
        useCase.setProductBuy(product, newStatus)
}
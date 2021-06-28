package com.fandiaspraja.sehatqcommerce.core.domain.usecase

import com.fandiaspraja.sehatqcommerce.core.data.Resource
import com.fandiaspraja.sehatqcommerce.core.domain.model.Category
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.domain.repository.ICommerceRepository
import kotlinx.coroutines.flow.Flow

class EcommerceInteractor(private val ecommerceRepository: ICommerceRepository): EcommerceUseCase {

    override fun getProduct(): Flow<Resource<List<Product>>> = ecommerceRepository.getProduct()

    override fun getCategory(): Flow<Resource<List<Category>>> = ecommerceRepository.getCategory()

    override fun setProductFavorite(product: Product, state: Boolean) = ecommerceRepository.setProductFavorite(product, state)

    override fun setProductBuy(product: Product, state: Boolean) = ecommerceRepository.setProductBuy(product, state)

    override fun getFavoriteProduct() = ecommerceRepository.getFavoriteProduct()

    override fun getBuyProduct() = ecommerceRepository.getBuyProduct()
}
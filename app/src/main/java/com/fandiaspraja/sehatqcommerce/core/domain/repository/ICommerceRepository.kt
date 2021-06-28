package com.fandiaspraja.sehatqcommerce.core.domain.repository

import com.fandiaspraja.sehatqcommerce.core.data.Resource
import com.fandiaspraja.sehatqcommerce.core.domain.model.Category
import com.fandiaspraja.sehatqcommerce.core.domain.model.Ecommerce
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ICommerceRepository {

    fun getProduct(): Flow<Resource<List<Product>>>

    fun getCategory(): Flow<Resource<List<Category>>>

    fun setProductFavorite(product: Product, state: Boolean)

    fun setProductBuy(product: Product, state: Boolean)

    fun getFavoriteProduct(): Flow<List<Product>>

    fun getBuyProduct(): Flow<List<Product>>

}
package com.fandiaspraja.sehatqcommerce.core.data.source.local

import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.CategoryEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.ProductEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.local.room.EcommerceDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val ecommerceDao: EcommerceDao) {

    fun getAllProduct(): Flow<List<ProductEntity>> = ecommerceDao.getAllProduct()

    fun getAllCategory(): Flow<List<CategoryEntity>> = ecommerceDao.getAllCategory()

    suspend fun insertCategory(categorytList: List<CategoryEntity>) = ecommerceDao.insertCategory(categorytList)

    fun getFavoriteProduct(): Flow<List<ProductEntity>> = ecommerceDao.getFavoriteProduct()

    fun getBuyProduct(): Flow<List<ProductEntity>> = ecommerceDao.getBuyProduct()

    suspend fun insertProduct(productList: List<ProductEntity>) = ecommerceDao.insertProduct(productList)

    fun updateFavoriteProduct(product: ProductEntity, newState: Boolean) {
        product.isFavorite = newState
        ecommerceDao.updateFavoriteProduct(product)
    }

    fun updateBuyProduct(product: ProductEntity, newState: Boolean) {
        product.isBuy = newState
        ecommerceDao.updateBuyProduct(product)
    }
}
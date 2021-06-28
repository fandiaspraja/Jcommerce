package com.fandiaspraja.sehatqcommerce.core.data.source.local.room

import androidx.room.*
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.CategoryEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EcommerceDao {

    @Query("SELECT * FROM product")
    fun getAllProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM category")
    fun getAllCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM product where isFavorite = 1")
    fun getFavoriteProduct(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product where isBuy = 1")
    fun getBuyProduct(): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: List<CategoryEntity>)

    @Update
    fun updateFavoriteProduct(product: ProductEntity)

    @Update
    fun updateBuyProduct(product: ProductEntity)
}
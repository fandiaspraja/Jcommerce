package com.fandiaspraja.sehatqcommerce.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.CategoryEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.ProductEntity

@Database(entities = [ProductEntity::class, CategoryEntity::class], version = 1, exportSchema = false)
abstract class EcommerceDatabase: RoomDatabase() {

    abstract fun ecommerceDao(): EcommerceDao
}
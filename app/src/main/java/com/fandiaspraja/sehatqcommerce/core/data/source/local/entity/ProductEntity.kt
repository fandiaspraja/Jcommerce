package com.fandiaspraja.sehatqcommerce.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
        val loved: Int,
        val price: String,
        val imageUrl: String,
        val description: String,
        @PrimaryKey
        val id: String,
        val title: String,
        var isFavorite: Boolean = false,
        var isBuy: Boolean = false
)
package com.fandiaspraja.sehatqcommerce.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class CategoryEntity(
    val imageUrl: String,
    val name: String,
    @PrimaryKey
    val id: Int
)
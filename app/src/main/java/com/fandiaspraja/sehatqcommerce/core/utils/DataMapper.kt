package com.fandiaspraja.sehatqcommerce.core.utils

import androidx.room.PrimaryKey
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.CategoryEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.local.entity.ProductEntity
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.CategoryItem
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.ProductPromoItem
import com.fandiaspraja.sehatqcommerce.core.domain.model.Category
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product

object DataMapper {

    fun mapResponsesToEntities(input: List<ProductPromoItem>): List<ProductEntity> {
        val productList = ArrayList<ProductEntity>()
        input.map {
            val product = ProductEntity(
                    loved = it.loved,
                    price = it.price,
                    imageUrl = it.imageUrl,
                    description = it.description,
                    id = it.id,
                    title = it.title,
                    isFavorite = false,
                    isBuy = false
            )
            productList.add(product)
        }
        return productList
    }

    fun mapResponsesToEntitiesCategory(input: List<CategoryItem>): List<CategoryEntity> {
        val categoryList = ArrayList<CategoryEntity>()
        input.map {
            val category = CategoryEntity(
                imageUrl = it.imageUrl,
                name = it.name,
                id = it.id
            )
            categoryList.add(category)
        }
        return categoryList
    }

    fun mapEntitiesToDomain(input: List<ProductEntity>): List<Product> =
            input.map {
                Product(
                        loved = it.loved,
                        price = it.price,
                        imageUrl = it.imageUrl,
                        description = it.description,
                        id = it.id,
                        title = it.title,
                        isFavorite = it.isFavorite,
                        isBuy = it.isBuy
                )
            }

    fun mapEntitiesToDomainCategory(input: List<CategoryEntity>): List<Category> =
        input.map {
            Category(
                imageUrl = it.imageUrl,
                name = it.name,
                id = it.id
            )
        }

    fun mapDomainToEntityCategory(input: Category) = CategoryEntity(
        imageUrl = input.imageUrl,
        name = input.name,
        id = input.id
    )

    fun mapDomainToEntity(input: Product) = ProductEntity(
            loved = input.loved,
            price = input.price,
            imageUrl = input.imageUrl,
            description = input.description,
            id = input.id,
            title = input.title,
            isFavorite = input.isFavorite,
            isBuy = input.isBuy
    )

}
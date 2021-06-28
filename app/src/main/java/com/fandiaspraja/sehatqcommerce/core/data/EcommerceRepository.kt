package com.fandiaspraja.sehatqcommerce.core.data


import com.fandiaspraja.sehatqcommerce.core.data.source.local.LocalDataSource
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.RemoteDataSource
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.network.ApiResponse
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.CategoryItem
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.ProductPromoItem
import com.fandiaspraja.sehatqcommerce.core.domain.model.Category
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.domain.repository.ICommerceRepository
import com.fandiaspraja.sehatqcommerce.core.utils.AppExecutors
import com.fandiaspraja.sehatqcommerce.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EcommerceRepository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
): ICommerceRepository {

    override fun getProduct(): Flow<Resource<List<Product>>> =
            object : NetworkBoundResource<List<Product>, List<ProductPromoItem>>() {

                override fun loadFromDB(): Flow<List<Product>> {
                    return localDataSource.getAllProduct().map {
                        DataMapper.mapEntitiesToDomain(it)
                    }

                }

                override fun shouldFetch(data: List<Product>?): Boolean =
                    true

                override suspend fun createCall(): Flow<ApiResponse<List<ProductPromoItem>>> =
                    remoteDataSource.getProduct()

                override suspend fun saveCallResult(data: List<ProductPromoItem>) {
                    val productList = DataMapper.mapResponsesToEntities(data)
                    localDataSource.insertProduct(productList)
                }


            }.asFlow()

    override fun getCategory(): Flow<Resource<List<Category>>> =
        object : NetworkBoundResource<List<Category>, List<CategoryItem>>() {
            override fun loadFromDB(): Flow<List<Category>> {
                return localDataSource.getAllCategory().map {
                    DataMapper.mapEntitiesToDomainCategory(it)
                }
            }

            override fun shouldFetch(data: List<Category>?): Boolean =
                true

            override suspend fun createCall(): Flow<ApiResponse<List<CategoryItem>>> =
                remoteDataSource.getCategory()

            override suspend fun saveCallResult(data: List<CategoryItem>) {
                val categoryList = DataMapper.mapResponsesToEntitiesCategory(data)
                localDataSource.insertCategory(categoryList)
            }


        }.asFlow()

    override fun setProductFavorite(product: Product, state: Boolean){
        val productEntity = DataMapper.mapDomainToEntity(product)
        appExecutors.diskIO().execute { localDataSource.updateFavoriteProduct(productEntity, state) }
    }

    override fun setProductBuy(product: Product, state: Boolean) {
        val productEntity = DataMapper.mapDomainToEntity(product)
        appExecutors.diskIO().execute { localDataSource.updateBuyProduct(productEntity, state) }
    }

    override fun getFavoriteProduct(): Flow<List<Product>>{
        return localDataSource.getFavoriteProduct().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getBuyProduct(): Flow<List<Product>>{
        return localDataSource.getBuyProduct().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }
}
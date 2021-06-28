package com.fandiaspraja.sehatqcommerce.core.data.source.remote

import android.util.Log
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.network.ApiResponse
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.network.ApiService
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.CategoryItem
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.EcommerceResource
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.ProductPromoItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getProduct(): Flow<ApiResponse<List<ProductPromoItem>>>{

        return flow {
            try {
                val response = apiService.getEcommerceData()
                val dataArray = response[0].data
                if (dataArray != null){
                    emit(ApiResponse.Success(response[0].data.productPromo))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCategory(): Flow<ApiResponse<List<CategoryItem>>>{

        return flow {
            try {
                val response = apiService.getEcommerceData()
                val dataArray = response[0].data
                if (dataArray != null){
                    emit(ApiResponse.Success(response[0].data.category))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}
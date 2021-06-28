package com.fandiaspraja.sehatqcommerce.core.data.source.remote.network

import com.fandiaspraja.sehatqcommerce.core.data.source.remote.response.EcommerceResourceResponse
import retrofit2.http.GET

interface ApiService {

    @GET("home")
    suspend fun getEcommerceData(): List<EcommerceResourceResponse>
}
package com.fandiaspraja.sehatqcommerce.core.di

import androidx.room.Room
import com.fandiaspraja.sehatqcommerce.core.data.EcommerceRepository
import com.fandiaspraja.sehatqcommerce.core.data.source.local.LocalDataSource
import com.fandiaspraja.sehatqcommerce.core.data.source.local.room.EcommerceDatabase
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.RemoteDataSource
import com.fandiaspraja.sehatqcommerce.core.data.source.remote.network.ApiService
import com.fandiaspraja.sehatqcommerce.core.domain.repository.ICommerceRepository
import com.fandiaspraja.sehatqcommerce.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<EcommerceDatabase>().ecommerceDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            EcommerceDatabase::class.java, "Sehatcommerce.db"
        ).fallbackToDestructiveMigration().build()
    }
}


val networkModule = module {
    single {
        OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }
    single {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://private-4639ce-ecommerce56.apiary-mock.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(get())
                .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<ICommerceRepository> { EcommerceRepository(get(), get(), get()) }
}
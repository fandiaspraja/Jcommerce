package com.fandiaspraja.sehatqcommerce

import android.app.Application
import com.fandiaspraja.sehatqcommerce.core.di.databaseModule
import com.fandiaspraja.sehatqcommerce.core.di.networkModule
import com.fandiaspraja.sehatqcommerce.core.di.repositoryModule
import com.fandiaspraja.sehatqcommerce.di.useCaseModule
import com.fandiaspraja.sehatqcommerce.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class SehatQCommerceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@SehatQCommerceApplication)
            modules(
                listOf(
                        databaseModule,
                        networkModule,
                        repositoryModule,
                        useCaseModule,
                        viewModelModule
                )
            )
        }
    }

}
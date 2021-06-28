package com.fandiaspraja.sehatqcommerce.di

import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceInteractor
import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceUseCase
import com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.cart.CartViewModel
import com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.feed.FeedViewModel
import com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.home.HomeViewModel
import com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.profile.ProfileViewModel
import com.fandiaspraja.sehatqcommerce.ui.detailproduct.DetailProductViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<EcommerceUseCase> { EcommerceInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { FeedViewModel(get()) }
    viewModel { DetailProductViewModel(get()) }
}
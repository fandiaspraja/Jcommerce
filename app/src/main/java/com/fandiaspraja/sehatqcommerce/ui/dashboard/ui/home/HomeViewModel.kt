package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceUseCase

class HomeViewModel(useCase: EcommerceUseCase) : ViewModel() {
    val category = useCase.getCategory().asLiveData()

    val product = useCase.getProduct().asLiveData()
}
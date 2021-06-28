package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceUseCase

class CartViewModel(private val useCase: EcommerceUseCase) : ViewModel() {

    val product = useCase.getBuyProduct().asLiveData()
}
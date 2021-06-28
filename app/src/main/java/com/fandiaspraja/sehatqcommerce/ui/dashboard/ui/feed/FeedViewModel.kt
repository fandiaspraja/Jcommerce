package com.fandiaspraja.sehatqcommerce.ui.dashboard.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fandiaspraja.sehatqcommerce.core.domain.model.Product
import com.fandiaspraja.sehatqcommerce.core.domain.usecase.EcommerceUseCase

class FeedViewModel(private val useCase: EcommerceUseCase) : ViewModel() {

    val product = useCase.getProduct().asLiveData()
}
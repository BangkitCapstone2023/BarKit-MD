package com.barkit.app.home.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.utils.Resource

class CartViewModel(private val renterUseCase: RenterUseCase) : ViewModel() {
    fun getListCartProduct(): LiveData<Resource<List<Product>>> =
        renterUseCase.getCartProduct().asLiveData()
}
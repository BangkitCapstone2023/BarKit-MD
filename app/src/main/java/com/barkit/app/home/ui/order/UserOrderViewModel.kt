package com.barkit.app.home.ui.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.Order
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.utils.Resource

class UserOrderViewModel(private val renterUseCase: RenterUseCase) : ViewModel() {
    val listOrder: LiveData<Resource<List<Order>>> = renterUseCase.getListOrder().asLiveData()
}
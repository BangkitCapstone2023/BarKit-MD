package com.barkit.app.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.domain.usecase.GeneralUseCase
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.utils.Resource

class OrderViewModel(
    private val generalUseCase: GeneralUseCase,
    private val renterUseCase: RenterUseCase
) : ViewModel() {
    fun getProductDetail(id: String): LiveData<Resource<ProductDetail>> =
        generalUseCase.getProductDetail(id).asLiveData()

    fun orderProduct(
        productId: String,
        deliveryAddress: String,
        startRentDate: String,
        endRentDate: String,
        quantityOrder: Int,
        paymentUse: String,
        courier: String
    ): LiveData<Resource<Boolean>> = renterUseCase.orderProduct(
        productId,
        deliveryAddress,
        startRentDate,
        endRentDate,
        quantityOrder,
        paymentUse,
        courier
    ).asLiveData()
}
package com.barkit.app.core.domain.repository

import com.barkit.app.core.domain.model.Order
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RenterRepository {
    fun isLogin(): Flow<Resource<Boolean>>

    fun orderProduct(
        productId: String,
        deliveryAddress: String,
        startRentDate: String,
        endRentDate: String,
        quantityOrder: Int,
        paymentUse: String,
        courier: String
    ): Flow<Resource<Boolean>>

    fun getListOrder(): Flow<Resource<List<Order>>>

    fun getProfile(): Flow<Resource<Renter>>

    fun logout(): Flow<Resource<Boolean>>
}
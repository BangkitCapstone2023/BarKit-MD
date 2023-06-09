package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.Order
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.domain.repository.RenterRepository
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class RenterUseCaseImpl(private val repository: RenterRepository) : RenterUseCase {
    override fun isLogin(): Flow<Resource<Boolean>> = repository.isLogin()

    override fun orderProduct(
        productId: String,
        deliveryAddress: String,
        startRentDate: String,
        endRentDate: String,
        quantityOrder: Int,
        paymentUse: String,
        courier: String
    ): Flow<Resource<Boolean>> = repository.orderProduct(
        productId,
        deliveryAddress,
        startRentDate,
        endRentDate,
        quantityOrder,
        paymentUse,
        courier
    )

    override fun getListOrder(): Flow<Resource<List<Order>>> = repository.getListOrder()

    override fun getProfile(): Flow<Resource<Renter>> = repository.getProfile()

    override fun logout(): Flow<Resource<Boolean>> = repository.logout()

    override fun addToCart(productId: String): Flow<Resource<Boolean>> = repository.addToCart(productId)

    override fun getCartProduct(): Flow<Resource<List<Product>>> = repository.getCartProduct()
}
package com.barkit.app.core.domain.model

data class Order(
    val deliveryAddress: String,
    val endRentDate: String,
    val quantityOrder: Int,
    val productId: String,
    val paymentUse: String,
    val startRentDate: String,
    val courier: String,
    val orderId: String,
    val status: String,
    val product: Product
)

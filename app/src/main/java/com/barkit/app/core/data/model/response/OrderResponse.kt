package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class OrderResponse(

    @field:SerializedName("delivery_address")
    val deliveryAddress: String,

    @field:SerializedName("end_rent_date")
    val endRentDate: String,

    @field:SerializedName("quantity_order")
    val quantityOrder: Int,

    @field:SerializedName("product_id")
    val productId: String,

    @field:SerializedName("payment_use")
    val paymentUse: String,

    @field:SerializedName("start_rent_date")
    val startRentDate: String,

    @field:SerializedName("kurir")
    val courier: String,

    @field:SerializedName("order_id")
    val orderId: String,

    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("product")
    val product: ProductResponse
)

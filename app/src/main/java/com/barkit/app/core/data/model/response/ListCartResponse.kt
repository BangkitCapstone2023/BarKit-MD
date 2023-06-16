package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ListCartResponse(

    @field:SerializedName("data")
    val data: CartData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class CartData(

    @field:SerializedName("renter_id")
    val renterId: String,

    @field:SerializedName("cart_id")
    val cartId: String,

    @field:SerializedName("username")
    val username: String,

    @field:SerializedName("resultCart")
    val resultCart: List<ProductResponse>
)

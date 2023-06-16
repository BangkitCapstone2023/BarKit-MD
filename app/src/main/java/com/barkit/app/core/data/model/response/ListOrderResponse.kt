package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ListOrderResponse(

    @field:SerializedName("data")
    val listOrderData: ListOrderData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class ListOrderData(
    @field:SerializedName("ordersData")
    val ordersData: List<OrderResponse>
)

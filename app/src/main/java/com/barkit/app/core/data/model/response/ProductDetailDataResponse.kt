package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ProductDetailDataResponse(

    @field:SerializedName("data")
    val data: ProductDetailResponse,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

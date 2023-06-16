package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class DashboardResponse(
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: DashboardDataResponse
)

data class DashboardDataResponse(
    @field:SerializedName("products")
    val products: List<ProductResponse>,
    @field:SerializedName("categories")
    val categories: List<CategoryResponse>
)

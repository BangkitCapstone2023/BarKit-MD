package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @field:SerializedName("quantity")
    val quantity: String,
    @field:SerializedName("sub_category")
    val subCategory: String,
    @field:SerializedName("price")
    val price: String,
    @field:SerializedName("imageUrl")
    val imageUrl: String,
    @field:SerializedName("product_id")
    val productId: String,
    @field:SerializedName("description")
    val description: String,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("category")
    val category: String,
    @field:SerializedName("image_id")
    val imageId: String,
    @field:SerializedName("create_at")
    val createAt: String,
    @field:SerializedName("lessor_id")
    val lessorId: String,
    @field:SerializedName("username")
    val username: String? = null
)

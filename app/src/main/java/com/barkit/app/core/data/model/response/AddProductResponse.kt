package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class AddProductResponse(
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("message")
    val message: String
)

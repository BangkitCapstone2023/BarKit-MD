package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class SearchResultResponse(

    @field:SerializedName("data")
    val data: List<SearchProductResponse>,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("subcategories")
    val subcategories: List<SubcategoryResponse>
)
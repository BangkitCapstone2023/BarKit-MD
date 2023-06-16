package com.barkit.app.core.domain.model

data class Category(
    val name: String,
    val subcategories: List<Subcategory>
)

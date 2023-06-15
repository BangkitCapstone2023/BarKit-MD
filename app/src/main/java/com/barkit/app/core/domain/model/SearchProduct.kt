package com.barkit.app.core.domain.model

data class SearchProduct(
    val quantity: String,
    val subCategory: String,
    val storeAddress: String,
    val price: String,
    val productId: String,
    val imageUrl: String,
    val storePhone: String,
    val title: String,
    val category: String,
    val storeFullName: String
)

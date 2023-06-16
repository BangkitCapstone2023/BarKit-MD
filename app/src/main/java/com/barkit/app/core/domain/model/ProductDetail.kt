package com.barkit.app.core.domain.model

data class ProductDetail(
	val quantity: String,
	val subCategory: String,
	val description: String,
	val title: String,
	val lessor: Lessor,
	val lessorId: String,
	val price: String,
	val imageUrl: String,
	val productId: String,
	val category: String,
	val imageId: String,
	val createAt: String,
	val username: String
)

data class Lessor(
	val storeAddress: String,
	val storePhone: String,
	val storeActive: Boolean,
	val fullName: String,
	val storeEmail: String,
	val storeFullName: String
)


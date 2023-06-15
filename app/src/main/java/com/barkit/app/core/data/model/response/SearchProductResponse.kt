package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class SearchProductResponse(

	@field:SerializedName("quantity")
	val quantity: String,

	@field:SerializedName("sub_category")
	val subCategory: String,

	@field:SerializedName("storeAddress")
	val storeAddress: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("storePhone")
	val storePhone: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("storeFullName")
	val storeFullName: String
)

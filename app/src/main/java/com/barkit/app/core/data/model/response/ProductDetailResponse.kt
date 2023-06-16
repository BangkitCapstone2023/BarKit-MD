package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class ProductDetailResponse(

	@field:SerializedName("quantity")
	val quantity: String,

	@field:SerializedName("sub_category")
	val subCategory: String,

	@field:SerializedName("description")
	val description: String,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("lessor")
	val lessorResponse: LessorResponse,

	@field:SerializedName("lessor_id")
	val lessorId: String,

	@field:SerializedName("price")
	val price: String,

	@field:SerializedName("imageUrl")
	val imageUrl: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("category")
	val category: String,

	@field:SerializedName("image_id")
	val imageId: String,

	@field:SerializedName("create_at")
	val createAt: String,

	@field:SerializedName("username")
	val username: String
)

data class LessorResponse(

	@field:SerializedName("storeAddress")
	val storeAddress: String,

	@field:SerializedName("storePhone")
	val storePhone: String,

	@field:SerializedName("storeActive")
	val storeActive: Boolean,

	@field:SerializedName("fullName")
	val fullName: String,

	@field:SerializedName("storeEmail")
	val storeEmail: String,

	@field:SerializedName("storeFullName")
	val storeFullName: String
)

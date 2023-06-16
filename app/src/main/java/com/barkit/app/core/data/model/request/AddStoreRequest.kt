package com.barkit.app.core.data.model.request

import com.google.gson.annotations.SerializedName

data class AddStoreRequest(

	@field:SerializedName("storeAddress")
	val storeAddress: String,

	@field:SerializedName("storePhone")
	val storePhone: String,

	@field:SerializedName("storeEmail")
	val storeEmail: String,

	@field:SerializedName("storeFullName")
	val storeFullName: String
)

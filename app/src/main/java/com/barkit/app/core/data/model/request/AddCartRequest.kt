package com.barkit.app.core.data.model.request

import com.google.gson.annotations.SerializedName

data class AddCartRequest(

	@field:SerializedName("cartQuantity")
	val cartQuantity: Int
)

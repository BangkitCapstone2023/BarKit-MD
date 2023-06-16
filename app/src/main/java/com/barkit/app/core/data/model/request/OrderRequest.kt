package com.barkit.app.core.data.model.request

import com.google.gson.annotations.SerializedName

data class OrderRequest(

	@field:SerializedName("endRentDate")
	val endRentDate: String,

	@field:SerializedName("paymentUse")
	val paymentUse: String,

	@field:SerializedName("deliveryAddress")
	val deliveryAddress: String,

	@field:SerializedName("quantityOrder")
	val quantityOrder: Int,

	@field:SerializedName("kurir")
	val kurir: String,

	@field:SerializedName("startRentDate")
	val startRentDate: String
)

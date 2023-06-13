package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class RentOrderResponse(

	@field:SerializedName("data")
	val data: RentOrderData,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Int
)

data class RentOrderData(

	@field:SerializedName("renter_id")
	val renterId: String,

	@field:SerializedName("delivery_address")
	val deliveryAddress: String,

	@field:SerializedName("end_rent_date")
	val endRentDate: String,

	@field:SerializedName("product_id")
	val productId: String,

	@field:SerializedName("quantity_order")
	val quantityOrder: Int,

	@field:SerializedName("payment_use")
	val paymentUse: String,

	@field:SerializedName("start_rent_date")
	val startRentDate: String,

	@field:SerializedName("kurir")
	val courier: String,

	@field:SerializedName("order_id")
	val orderId: String,

	@field:SerializedName("lessor_id")
	val lessorId: String,

	@field:SerializedName("status")
	val status: String
)

package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class StoreResponse(

    @field:SerializedName("renter_id")
    val renterId: String,

    @field:SerializedName("storeAddress")
    val storeAddress: String,

    @field:SerializedName(value = "kurirId", alternate = ["kurir"])
    val courierId: String,

    @field:SerializedName("storePhone")
    val storePhone: String,

    @field:SerializedName("storeActive")
    val storeActive: Boolean,

    @field:SerializedName("fullName")
    val fullName: String,

    @field:SerializedName("storeEmail")
    val storeEmail: String,

    @field:SerializedName("storeFullName")
    val storeFullName: String,

    @field:SerializedName("lessor_id")
    val lessorId: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("username")
    val username: String
)

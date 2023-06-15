package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class LogoutResponse(

    @field:SerializedName("data")
    val data: LogoutData,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("status")
    val status: Int
)

data class LogoutData(

    @field:SerializedName("time")
    val time: String,

    @field:SerializedName("email")
    val email: String
)

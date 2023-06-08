package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("data")
    val data: Data,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("status")
    val status: Int
)

data class Data(
    @field:SerializedName("renter_id")
    val renterId: String,
    @field:SerializedName("address")
    val address: String,
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("fullName")
    val fullName: String,
    @field:SerializedName("userRecordDatas")
    val userRecordData: UserRecordData,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("username")
    val username: String
)

data class UserRecordData(
    @field:SerializedName("emailVerified")
    val emailVerified: Boolean,
    @field:SerializedName("lastSignInTime")
    val lastSignInTime: Any,
    @field:SerializedName("creationTime")
    val creationTime: String,
    @field:SerializedName("lastRefreshTime")
    val lastRefreshTime: Any
)

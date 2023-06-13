package com.barkit.app.core.data.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @field:SerializedName("status")
    val status: Int,
    @field:SerializedName("message")
    val message: String,
    @field:SerializedName("data")
    val data: UserResponse,
)

data class UserResponse(
    @field:SerializedName("token")
    val token: String,
    @field:SerializedName("renter")
    val renter: RenterResponse,
)

data class RenterResponse(
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
    @field:SerializedName("isLessor")
    val isLessor: Boolean,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("email_verified")
    val emailVerified: Boolean
)

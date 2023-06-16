package com.barkit.app.core.data.model.request

import com.google.gson.annotations.SerializedName

data class RegisterRequest(
    @field:SerializedName("fullName")
    val fullName: String,
    @field:SerializedName("username")
    val username: String,
    @field:SerializedName("email")
    val email: String,
    @field:SerializedName("password")
    val password: String,
    @field:SerializedName("address")
    val address: String,
    @field:SerializedName("phone")
    val phone: String,
    @field:SerializedName("gender")
    val gender: String,
)

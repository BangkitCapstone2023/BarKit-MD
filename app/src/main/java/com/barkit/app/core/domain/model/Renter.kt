package com.barkit.app.core.domain.model

data class Renter(
    val renterId: String,
    val address: String,
    val gender: String,
    val phone: String,
    val fullName: String,
    val username: String,
)

package com.barkit.app.core.domain.model

data class User(
    val email: String,
    val token: String,
    val renter: Renter,
)

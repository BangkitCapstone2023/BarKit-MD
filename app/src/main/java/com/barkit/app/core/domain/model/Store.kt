package com.barkit.app.core.domain.model

data class Store(
    val renterId: String,
    val storeAddress: String,
    val courierId: String,
    val storePhone: String,
    val storeActive: Boolean,
    val fullName: String,
    val storeEmail: String,
    val storeFullName: String,
    val lessorId: String,
    val email: String,
    val username: String
)
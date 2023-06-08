package com.barkit.app.core.domain.repository

import com.barkit.app.core.domain.model.User
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String): Flow<Resource<User>>

    fun register(
        fullName: String,
        username: String,
        email: String,
        password: String,
        address: String,
        phone: String,
        gender: String
    ): Flow<Resource<Boolean>>

    fun isLogin(): Flow<Boolean>

    fun logout(): Flow<Nothing>
}
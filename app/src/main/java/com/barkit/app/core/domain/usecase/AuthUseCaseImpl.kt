package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.User
import com.barkit.app.core.domain.repository.AuthRepository
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class AuthUseCaseImpl(private val repository: AuthRepository) : AuthUseCase {
    override fun login(identifier: String, password: String): Flow<Resource<User>> =
        repository.login(identifier, password)

    override fun register(
        fullName: String,
        username: String,
        email: String,
        password: String,
        address: String,
        phone: String,
        gender: String
    ): Flow<Resource<Boolean>> =
        repository.register(fullName, username, email, password, address, phone, gender)


    override fun isLogin(): Flow<Boolean> = repository.isLogin()

    override fun logout(): Flow<Nothing> = repository.logout()
}
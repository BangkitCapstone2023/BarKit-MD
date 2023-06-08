package com.barkit.app.core.data.repository

import android.util.Log
import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.data.model.request.LoginRequest
import com.barkit.app.core.data.model.request.RegisterRequest
import com.barkit.app.core.domain.model.User
import com.barkit.app.core.domain.repository.AuthRepository
import com.barkit.app.core.utils.Resource
import com.barkit.app.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AuthRepositoryImpl(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : AuthRepository {
    override fun login(email: String, password: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())

        try {
            val loginRequest = LoginRequest(email, password)
            val response = apiService.login(loginRequest)
            val user = response.data.toDomainModel()

            sessionManager.createLoginSession()
            sessionManager.saveUserToken(user.token)

            emit(Resource.Success(user))
        } catch (e: Exception) {
            Log.e(TAG, "login: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun register(
        fullName: String,
        username: String,
        email: String,
        password: String,
        address: String,
        phone: String,
        gender: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val registerRequest = RegisterRequest(
                fullName, username, email, password, address, phone, gender
            )
            val response = apiService.register(registerRequest)

            if (response.status == 201) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            Log.e(TAG, "register: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun isLogin(): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun logout(): Flow<Nothing> {
        TODO("Not yet implemented")
    }

    private companion object {
        const val TAG = "AuthRepositoryImpl"
    }
}
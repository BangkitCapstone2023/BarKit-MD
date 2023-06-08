package com.barkit.app.core.data

import com.barkit.app.core.data.model.request.LoginRequest
import com.barkit.app.core.data.model.request.RegisterRequest
import com.barkit.app.core.data.model.response.LoginResponse
import com.barkit.app.core.data.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse
}
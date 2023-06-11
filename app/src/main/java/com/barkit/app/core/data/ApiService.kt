package com.barkit.app.core.data

import com.barkit.app.core.data.model.request.LoginRequest
import com.barkit.app.core.data.model.request.RegisterRequest
import com.barkit.app.core.data.model.response.DashboardResponse
import com.barkit.app.core.data.model.response.LoginResponse
import com.barkit.app.core.data.model.response.ProductDetailDataResponse
import com.barkit.app.core.data.model.response.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body registerRequest: RegisterRequest
    ): RegisterResponse

    @GET("home")
    suspend fun getDashboard(): DashboardResponse

    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") id: String
    ): ProductDetailDataResponse
}
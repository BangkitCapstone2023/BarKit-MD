package com.barkit.app.core.data

import com.barkit.app.core.data.model.request.LoginRequest
import com.barkit.app.core.data.model.request.OrderRequest
import com.barkit.app.core.data.model.request.RegisterRequest
import com.barkit.app.core.data.model.response.DashboardResponse
import com.barkit.app.core.data.model.response.ListOrderResponse
import com.barkit.app.core.data.model.response.LoginResponse
import com.barkit.app.core.data.model.response.ProductDetailDataResponse
import com.barkit.app.core.data.model.response.RegisterResponse
import com.barkit.app.core.data.model.response.RentOrderResponse
import com.barkit.app.core.data.model.response.RenterProfileResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
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

    @POST("{username}/orders/{id}")
    suspend fun orderProduct(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Path("id") id: String,
        @Body orderRequest: OrderRequest
    ): RentOrderResponse

    @GET("{username}/orders")
    suspend fun getListOrder(
        @Header("Authorization") token: String,
        @Path("username") username: String,
    ): ListOrderResponse

    @GET("{username}/profile")
    suspend fun getRenterProfile(
        @Header("Authorization") token: String,
        @Path("username") username: String,
    ): RenterProfileResponse
}
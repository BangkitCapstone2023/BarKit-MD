package com.barkit.app.core.data

import com.barkit.app.core.data.model.request.AddStoreRequest
import com.barkit.app.core.data.model.request.LoginRequest
import com.barkit.app.core.data.model.request.OrderRequest
import com.barkit.app.core.data.model.request.RegisterRequest
import com.barkit.app.core.data.model.response.AddProductResponse
import com.barkit.app.core.data.model.response.AddStoreResponse
import com.barkit.app.core.data.model.response.DashboardResponse
import com.barkit.app.core.data.model.response.ListOrderResponse
import com.barkit.app.core.data.model.response.LoginResponse
import com.barkit.app.core.data.model.response.ProductDetailDataResponse
import com.barkit.app.core.data.model.response.RegisterResponse
import com.barkit.app.core.data.model.response.RentOrderResponse
import com.barkit.app.core.data.model.response.RenterProfileResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
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

    @POST("lessors/{username}/register")
    suspend fun addStore(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Body addStoreRequest: AddStoreRequest
    ): AddStoreResponse

    @GET("lessors/{username}/profile")
    suspend fun getStoreProfile(
        @Header("Authorization") token: String,
        @Path("username") username: String
    ): AddStoreResponse

    @Multipart
    @POST("lessors/{username}/products")
    suspend fun addProduct(
        @Header("Authorization") token: String,
        @Path("username") username: String,
        @Part file: MultipartBody.Part,
        @Part("title") title: RequestBody,
        @Part("description") description: RequestBody,
        @Part("price") price: RequestBody,
        @Part("category") category: RequestBody,
        @Part("subCategory") subCategory: RequestBody,
        @Part("quantity") quantity: RequestBody,
    ): AddProductResponse
}
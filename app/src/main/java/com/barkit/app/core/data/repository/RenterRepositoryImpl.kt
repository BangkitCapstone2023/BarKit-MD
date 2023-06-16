package com.barkit.app.core.data.repository

import android.util.Log
import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.data.model.request.AddCartRequest
import com.barkit.app.core.data.model.request.OrderRequest
import com.barkit.app.core.domain.model.Order
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.domain.repository.RenterRepository
import com.barkit.app.core.utils.Resource
import com.barkit.app.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class RenterRepositoryImpl(
    private val apiService: ApiService,
    private val sessionManager: SessionManager,
) : RenterRepository {
    override fun isLogin(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val isLogin = sessionManager.isLogin().first()

            if (isLogin) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Empty Session!"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "isLogin: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun orderProduct(
        productId: String,
        deliveryAddress: String,
        startRentDate: String,
        endRentDate: String,
        quantityOrder: Int,
        paymentUse: String,
        courier: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val request = OrderRequest(
                deliveryAddress = deliveryAddress,
                startRentDate = startRentDate,
                endRentDate = endRentDate,
                quantityOrder = quantityOrder,
                paymentUse = paymentUse,
                kurir = courier
            )
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val response = apiService.orderProduct("Bearer $token", username, productId, request)

            if (response.status == 201) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error("Failed to post order!"))
            }
        } catch (e: Exception) {
            Log.e(TAG, "orderProduct: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getListOrder(): Flow<Resource<List<Order>>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val response = apiService.getListOrder("Bearer $token", username)
            val listOrder = response.listOrderData.ordersData.map { it.toDomainModel() }

            emit(Resource.Success(listOrder))
        } catch (e: Exception) {
            Log.e(TAG, "getListOrder: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getProfile(): Flow<Resource<Renter>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val response = apiService.getRenterProfile("Bearer $token", username)
            val renter = response.data.toDomainModel()

            emit(Resource.Success(renter))
        } catch (e: Exception) {
            Log.e(TAG, "getProfile: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun logout(): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val response = apiService.logout("Bearer $token")

            if (response.status == 200) {
                sessionManager.clearLoginSession()
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            Log.e(TAG, "logout: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun addToCart(productId: String): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val requestBody = AddCartRequest(1)

            apiService.addToCart("Bearer $token", username, productId, requestBody)

            emit(Resource.Success(true))
        } catch (e: Exception) {
            Log.e(TAG, "addToCart: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getCartProduct(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val response = apiService.getListCart("Bearer $token", username)
            val listProduct = response.data.resultCart.map { it.toDomainModel() }

            emit(Resource.Success(listProduct))
        } catch (e: Exception) {
            Log.e(TAG, "getCartProduct: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    private companion object {
        const val TAG = "RenterRepositoryImpl"
    }
}
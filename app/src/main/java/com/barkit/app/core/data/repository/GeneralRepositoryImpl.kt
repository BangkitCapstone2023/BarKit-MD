package com.barkit.app.core.data.repository

import android.util.Log
import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.core.domain.repository.GeneralRepository
import com.barkit.app.core.utils.Resource
import com.barkit.app.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GeneralRepositoryImpl(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : GeneralRepository {
    override fun getDashboard(): Flow<Resource<DashboardData>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.getDashboard()
            val dashboardData = response.data.toDomainModel()

            emit(Resource.Success(dashboardData))
        } catch (e: Exception) {
            Log.e(TAG, "getAllProducts: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getProductDetail(id: String): Flow<Resource<ProductDetail>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.getProductDetail(id)
            val productDetail = response.data.toDomainModel()

            emit(Resource.Success(productDetail))
        } catch (e: Exception) {
            Log.e(TAG, "getProductDetail: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun searchProduct(query: String): Flow<Resource<List<SearchProduct>>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.searchProduct(query)
            val listProduct = response.data.map { it.toDomainModel() }

            emit(Resource.Success(listProduct))
        } catch (e: Exception) {
            Log.e(TAG, "searchProduct: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    private companion object {
        const val TAG = "ProductRepositoryImpl"
    }
}
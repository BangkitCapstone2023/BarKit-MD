package com.barkit.app.core.data.repository

import android.util.Log
import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.repository.RenterRepository
import com.barkit.app.core.utils.Resource
import com.barkit.app.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RenterRepositoryImpl(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : RenterRepository {
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

    private companion object {
        const val TAG = "ProductRepositoryImpl"
    }
}
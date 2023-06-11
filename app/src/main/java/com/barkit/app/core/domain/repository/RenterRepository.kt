package com.barkit.app.core.domain.repository

import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RenterRepository {
    fun getDashboard(): Flow<Resource<DashboardData>>
}
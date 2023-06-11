package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface RenterUseCase {
    fun getDashboard(): Flow<Resource<DashboardData>>
}
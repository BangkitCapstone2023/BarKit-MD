package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.repository.RenterRepository
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class RenterUseCaseImpl(private val repository: RenterRepository) : RenterUseCase {
    override fun getDashboard(): Flow<Resource<DashboardData>> = repository.getDashboard()
}
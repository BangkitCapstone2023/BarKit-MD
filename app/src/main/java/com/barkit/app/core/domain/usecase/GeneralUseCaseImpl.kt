package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.core.domain.repository.GeneralRepository
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class GeneralUseCaseImpl(private val repository: GeneralRepository) : GeneralUseCase {
    override fun getDashboard(): Flow<Resource<DashboardData>> = repository.getDashboard()
    override fun getProductDetail(id: String): Flow<Resource<ProductDetail>> =
        repository.getProductDetail(id)

    override fun searchProduct(query: String): Flow<Resource<List<SearchProduct>>> = repository.searchProduct(query)
}
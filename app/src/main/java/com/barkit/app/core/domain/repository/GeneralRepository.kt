package com.barkit.app.core.domain.repository

import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GeneralRepository {
    fun getDashboard(): Flow<Resource<DashboardData>>
    fun getProductDetail(id: String): Flow<Resource<ProductDetail>>
    fun searchProduct(query: String): Flow<Resource<List<SearchProduct>>>
}
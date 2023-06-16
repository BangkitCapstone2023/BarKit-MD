package com.barkit.app.core.domain.repository

import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Store
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface LessorRepository {
    fun addLessor(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): Flow<Resource<Boolean>>

    fun getStoreProfile(): Flow<Resource<Store>>

    fun getListCategory(): Flow<Resource<List<Category>>>

    fun addProduct(
        file: File,
        title: String,
        description: String,
        price: Int,
        category: String,
        subCategory: String,
        quantity: Int,
    ): Flow<Resource<Boolean>>
}
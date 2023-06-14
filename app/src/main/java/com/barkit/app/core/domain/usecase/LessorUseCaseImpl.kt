package com.barkit.app.core.domain.usecase

import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Store
import com.barkit.app.core.domain.repository.LessorRepository
import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow
import java.io.File

class LessorUseCaseImpl(private val repository: LessorRepository) : LessorUseCase {
    override fun addLessor(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): Flow<Resource<Boolean>> = repository.addLessor(fullName, address, email, phone)

    override fun getStoreProfile(): Flow<Resource<Store>> = repository.getStoreProfile()

    override fun getListCategory(): Flow<Resource<List<Category>>> = repository.getListCategory()

    override fun addProduct(
        file: File,
        title: String,
        description: String,
        price: Int,
        category: String,
        subCategory: String,
        quantity: Int
    ): Flow<Resource<Boolean>> =
        repository.addProduct(file, title, description, price, category, subCategory, quantity)
}
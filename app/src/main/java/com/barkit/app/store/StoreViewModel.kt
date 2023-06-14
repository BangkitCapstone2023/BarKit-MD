package com.barkit.app.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Store
import com.barkit.app.core.domain.usecase.LessorUseCase
import com.barkit.app.core.utils.Resource
import java.io.File

class StoreViewModel(private val lessorUseCase: LessorUseCase) : ViewModel() {
    val storeProfile: LiveData<Resource<Store>> = lessorUseCase.getStoreProfile().asLiveData()

    val categories: LiveData<Resource<List<Category>>> =
        lessorUseCase.getListCategory().asLiveData()

    fun addProduct(
        file: File,
        title: String,
        description: String,
        price: Int,
        category: String,
        subCategory: String,
        quantity: Int,
    ): LiveData<Resource<Boolean>> =
        lessorUseCase.addProduct(file, title, description, price, category, subCategory, quantity)
            .asLiveData()
}
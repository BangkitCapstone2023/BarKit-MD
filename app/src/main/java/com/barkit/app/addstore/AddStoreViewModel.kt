package com.barkit.app.addstore

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.usecase.LessorUseCase
import com.barkit.app.core.utils.Resource

class AddStoreViewModel(private val lessorUseCase: LessorUseCase) : ViewModel() {
    fun addStore(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): LiveData<Resource<Boolean>> =
        lessorUseCase.addLessor(fullName, address, email, phone).asLiveData()
}
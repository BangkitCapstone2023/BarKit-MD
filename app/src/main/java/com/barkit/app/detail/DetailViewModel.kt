package com.barkit.app.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.domain.usecase.GeneralUseCase
import com.barkit.app.core.utils.Resource

class DetailViewModel(private val generalUseCase: GeneralUseCase) : ViewModel() {
    fun getProductDetail(id: String): LiveData<Resource<ProductDetail>> =
        generalUseCase.getProductDetail(id).asLiveData()
}
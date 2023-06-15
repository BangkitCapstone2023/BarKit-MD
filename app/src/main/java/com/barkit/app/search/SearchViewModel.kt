package com.barkit.app.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.core.domain.usecase.GeneralUseCase
import com.barkit.app.core.utils.Resource

class SearchViewModel(private val generalUseCase: GeneralUseCase) : ViewModel() {
    fun searchProduct(query: String): LiveData<Resource<List<SearchProduct>>> =
        generalUseCase.searchProduct(query).asLiveData()
}
package com.barkit.app.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.utils.Resource

class MainViewModel(renterUseCase: RenterUseCase) : ViewModel() {
    val isLogin: LiveData<Resource<Boolean>> = renterUseCase.isLogin().asLiveData()
}
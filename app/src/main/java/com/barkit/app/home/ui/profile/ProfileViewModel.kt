package com.barkit.app.home.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.utils.Resource

class ProfileViewModel(private val renterUseCase: RenterUseCase) : ViewModel() {
    val renterProfile: LiveData<Resource<Renter>> = renterUseCase.getProfile().asLiveData()

    fun logout(): LiveData<Resource<Boolean>> = renterUseCase.logout().asLiveData()
}
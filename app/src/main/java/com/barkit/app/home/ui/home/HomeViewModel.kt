package com.barkit.app.home.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.usecase.GeneralUseCase
import com.barkit.app.core.utils.Resource

class HomeViewModel(useCase: GeneralUseCase) : ViewModel() {
    val dashboardData: LiveData<Resource<DashboardData>> = useCase.getDashboard().asLiveData()
}
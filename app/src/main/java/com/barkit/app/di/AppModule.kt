package com.barkit.app.di

import com.barkit.app.authentication.AuthViewModel
import com.barkit.app.detail.DetailViewModel
import com.barkit.app.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}
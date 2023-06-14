package com.barkit.app.di

import com.barkit.app.addstore.AddStoreViewModel
import com.barkit.app.authentication.AuthViewModel
import com.barkit.app.detail.DetailViewModel
import com.barkit.app.home.ui.home.HomeViewModel
import com.barkit.app.home.ui.order.UserOrderViewModel
import com.barkit.app.home.ui.profile.ProfileViewModel
import com.barkit.app.main.MainViewModel
import com.barkit.app.order.OrderViewModel
import com.barkit.app.store.StoreViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { AuthViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { OrderViewModel(get(), get()) }
    viewModel { UserOrderViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { AddStoreViewModel(get()) }
    viewModel { StoreViewModel(get()) }
}
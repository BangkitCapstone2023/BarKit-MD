package com.barkit.app.authentication

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.barkit.app.core.domain.model.User
import com.barkit.app.core.domain.usecase.AuthUseCase
import com.barkit.app.core.utils.Resource

class AuthViewModel(private val useCase: AuthUseCase) : ViewModel() {
    fun login(identifier: String, password: String): LiveData<Resource<User>> =
        useCase.login(identifier, password).asLiveData()

    fun register(
        fullName: String,
        username: String,
        email: String,
        password: String,
        address: String,
        phone: String,
        gender: String
    ): LiveData<Resource<Boolean>> =
        useCase.register(fullName, username, email, password, address, phone, gender).asLiveData()

    fun logout(): LiveData<Nothing> = useCase.logout().asLiveData()
}
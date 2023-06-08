package com.barkit.app.utils

import android.content.Context
import android.util.Patterns
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.barkit.app.core.data.model.response.RenterResponse
import com.barkit.app.core.data.model.response.UserResponse
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.domain.model.User

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session_datastore")

fun String.isValidEmail(): Boolean {
    return this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    return this.isNotEmpty() && this.length >= 6
}

fun UserResponse.toDomainModel(): User = User(email, token, renter.toDomainModel())

fun RenterResponse.toDomainModel(): Renter = Renter(
    renterId, address, gender, phone, fullName, username
)
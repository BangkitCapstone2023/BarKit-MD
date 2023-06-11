package com.barkit.app.utils

import android.content.Context
import android.util.Patterns
import android.widget.ImageView
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.barkit.app.core.data.model.response.CategoryResponse
import com.barkit.app.core.data.model.response.DashboardDataResponse
import com.barkit.app.core.data.model.response.ProductResponse
import com.barkit.app.core.data.model.response.RenterResponse
import com.barkit.app.core.data.model.response.SubcategoryResponse
import com.barkit.app.core.data.model.response.UserResponse
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.DashboardData
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.domain.model.Renter
import com.barkit.app.core.domain.model.Subcategory
import com.barkit.app.core.domain.model.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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

fun DashboardDataResponse.toDomainModel(): DashboardData = DashboardData(
    products.map { it.toDomainModel() },
    categories.map { it.toDomainModel() }
)

fun ProductResponse.toDomainModel(): Product = Product(
    quantity,
    subCategory,
    price,
    imageUrl,
    productId,
    description,
    title,
    category,
    imageId,
    createAt,
    lessorId,
    username
)

fun CategoryResponse.toDomainModel(): Category = Category(
    name,
    subcategories.map { it.toDomainModel() }
)

fun SubcategoryResponse.toDomainModel(): Subcategory = Subcategory(name)

fun ImageView.loadImage(url: String, requestOptions: RequestOptions = RequestOptions()) {
    Glide.with(this.context)
        .load(url)
        .apply(requestOptions)
        .into(this)
}
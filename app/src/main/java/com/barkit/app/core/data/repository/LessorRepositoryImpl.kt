package com.barkit.app.core.data.repository

import android.util.Log
import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.data.model.request.AddStoreRequest
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Store
import com.barkit.app.core.domain.repository.LessorRepository
import com.barkit.app.core.utils.Resource
import com.barkit.app.utils.toDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import java.io.File

class LessorRepositoryImpl(
    private val apiService: ApiService,
    private val sessionManager: SessionManager
) : LessorRepository {
    override fun addLessor(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val request = AddStoreRequest(
                storeAddress = address,
                storeEmail = email,
                storePhone = phone,
                storeFullName = fullName
            )
            val response = apiService.addStore("Bearer $token", username, request)

            if (response.status == 201) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: Exception) {
            Log.e(TAG, "addLessor: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getStoreProfile(): Flow<Resource<Store>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val response = apiService.getStoreProfile("Bearer $token", username)
            val store = response.data.toDomainModel()

            emit(Resource.Success(store))
        } catch (e: Exception) {
            Log.e(TAG, "getStoreProfile: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getListCategory(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.getDashboard()
            val categories = response.data.categories.map { it.toDomainModel() }

            emit(Resource.Success(categories))
        } catch (e: Exception) {
            Log.e(TAG, "getListCategory: $e")
            emit(Resource.Error(e.toString()))
        }
    }

    override fun addProduct(
        file: File,
        title: String,
        description: String,
        price: Int,
        category: String,
        subCategory: String,
        quantity: Int
    ): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())

        try {
            val token = sessionManager.getUserToken().first()
            val username = sessionManager.getUsername().first()
            val image = file.asRequestBody("image/jpeg".toMediaType())
            val imageMultipart: MultipartBody.Part = MultipartBody.Part.createFormData(
                "image",
                file.name,
                image
            )
            val bodyTitle = title.toRequestBody("text/plain".toMediaType())
            val bodyDescription = description.toRequestBody("text/plain".toMediaType())
            val bodyPrice = price.toString().toRequestBody("text/plain".toMediaType())
            val bodyCategory = category.toRequestBody("text/plain".toMediaType())
            val bodySubCategory = subCategory.toRequestBody("text/plain".toMediaType())
            val bodyQuantity = quantity.toString().toRequestBody("text/plain".toMediaType())

            val response = apiService.addProduct(
                "Bearer $token",
                username,
                imageMultipart,
                bodyTitle,
                bodyDescription,
                bodyPrice,
                bodyCategory,
                bodySubCategory,
                bodyQuantity
            )

            if (response.status == 200) {
                emit(Resource.Success(true))
            } else {
                emit(Resource.Error(response.message))
            }
        } catch (e: HttpException) {
            Log.e(TAG, "addProduct: $e")

            if (e.code() == 400) {
                emit(Resource.Error("Category dan gambar yang di input tidak sesuai"))
            } else {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    private companion object {
        const val TAG = "LessorRepositoryImpl"
    }
}
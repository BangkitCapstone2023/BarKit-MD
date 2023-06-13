package com.barkit.app.core.domain.repository

import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

interface LessorRepository {
    fun addLessor(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): Flow<Resource<Boolean>>
}
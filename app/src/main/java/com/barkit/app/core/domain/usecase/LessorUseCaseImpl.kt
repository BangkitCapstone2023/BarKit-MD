package com.barkit.app.core.domain.usecase

import com.barkit.app.core.utils.Resource
import kotlinx.coroutines.flow.Flow

class LessorUseCaseImpl : LessorUseCase {
    override fun addLessor(
        fullName: String,
        address: String,
        email: String,
        phone: String
    ): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}
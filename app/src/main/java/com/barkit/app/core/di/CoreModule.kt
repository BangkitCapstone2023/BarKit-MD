package com.barkit.app.core.di

import com.barkit.app.core.data.ApiService
import com.barkit.app.core.data.SessionManager
import com.barkit.app.core.data.repository.AuthRepositoryImpl
import com.barkit.app.core.data.repository.RenterRepositoryImpl
import com.barkit.app.core.domain.repository.AuthRepository
import com.barkit.app.core.domain.repository.RenterRepository
import com.barkit.app.core.domain.usecase.AuthUseCase
import com.barkit.app.core.domain.usecase.AuthUseCaseImpl
import com.barkit.app.core.domain.usecase.RenterUseCase
import com.barkit.app.core.domain.usecase.RenterUseCaseImpl
import com.barkit.app.utils.dataStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataModule = module {
    single {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://barkit-app-iba3thqjqq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        retrofit.create(ApiService::class.java)
    }

    single { SessionManager(androidContext().dataStore) }
}

val repositoryModule = module {
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
    single<RenterRepository> { RenterRepositoryImpl(get(), get()) }
}

val useCaseModule = module {
    single<AuthUseCase> { AuthUseCaseImpl(get()) }
    single<RenterUseCase> { RenterUseCaseImpl(get()) }
}
package com.barkit.app

import android.app.Application
import com.barkit.app.core.di.dataModule
import com.barkit.app.core.di.repositoryModule
import com.barkit.app.core.di.useCaseModule
import com.barkit.app.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)

            modules(
                listOf(
                    dataModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}
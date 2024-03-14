package com.example.warg

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
class WargApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WargApplication)
            modules(appModule)
            modules(commonModule)
        }
    }
}
package com.mr.nemo.dragonfly.ui

import android.app.Application
import com.mr.nemo.dragonfly.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DragonFlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@DragonFlyApplication)
            modules(appModule)
        }
    }
}

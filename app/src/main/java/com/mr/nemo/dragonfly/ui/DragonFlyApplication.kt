package com.mr.nemo.dragonfly.ui

import android.app.Application
import com.mr.nemo.dragonfly.di.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class DragonFlyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DragonFlyApplication)
            androidLogger()
            modules(AppModule().module)
        }
    }
}

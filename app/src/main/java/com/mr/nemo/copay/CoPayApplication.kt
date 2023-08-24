package com.mr.nemo.copay

import android.app.Application
import com.mr.nemo.copay.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class CoPayApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CoPayApplication)
            modules(appModule)
        }
    }
}

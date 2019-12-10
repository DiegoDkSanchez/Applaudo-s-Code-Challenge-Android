package com.example.challengeaplaudo

import android.app.Application
import com.example.challengeaplaudo.modules.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application(){
    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger()
            androidContext(this@BaseApp)
            modules(ViewModelModule)
        }
    }
}
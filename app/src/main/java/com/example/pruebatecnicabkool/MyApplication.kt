package com.example.pruebatecnicabkool

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application(){

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
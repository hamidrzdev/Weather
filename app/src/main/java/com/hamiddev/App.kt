package com.hamiddev

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
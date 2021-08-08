package com.hamiddev

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App :Application(){
    override fun onCreate() {
        Fresco.initialize(this)
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}
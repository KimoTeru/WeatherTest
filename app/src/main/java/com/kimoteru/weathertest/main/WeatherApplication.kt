package com.kimoteru.weathertest.main

import android.app.Application
import com.kimoteru.weathertest.core.coreModule
import com.kimoteru.weathertest.features.featuresModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class WeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@WeatherApplication)
            modules(coreModule() + featuresModule())
        }
    }
}
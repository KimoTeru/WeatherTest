package com.kimoteru.weathertest.core.core_data

import androidx.room.Room
import com.kimoteru.weathertest.core.core_data.storage.WeatherDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun storageModule() = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            WeatherDatabase::class.java,
            WeatherDatabase.NAME
        ).build()
    }

    single { get<WeatherDatabase>().dao }
}
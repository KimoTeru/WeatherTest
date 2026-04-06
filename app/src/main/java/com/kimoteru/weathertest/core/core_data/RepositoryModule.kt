package com.kimoteru.weathertest.core.core_data

import com.kimoteru.weathertest.BuildConfig
import com.kimoteru.weathertest.core.core_data.domain.WeatherRepository
import com.kimoteru.weathertest.core.core_data.storage.WeatherRepositoryImpl
import com.kimoteru.weathertest.core.core_data.domain.HistoryRepository
import com.kimoteru.weathertest.core.core_data.storage.HistoryRepositoryImpl
import org.koin.dsl.module

fun repositoryModule() = module {
    single<WeatherRepository> {
        WeatherRepositoryImpl(
            weatherApi = get(),
            apiKey = BuildConfig.API_KEY
        )
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(dao = get())
    }
}
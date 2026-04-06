package com.kimoteru.weathertest.features.weather.data

import com.kimoteru.weathertest.features.weather.domain.WeatherFeatureRepository
import org.koin.dsl.module

fun weatherDataModule() = module {
    single<WeatherFeatureRepository> { WeatherFeatureRepositoryImpl(get(), get()) }
}
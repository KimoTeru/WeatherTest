package com.kimoteru.weathertest.features.weather.domain.usecase

import org.koin.dsl.module

fun weatherDomainModule() = module {
    factory { GetWeatherUseCase(get()) }
}
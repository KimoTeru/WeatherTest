package com.kimoteru.weathertest.features.weather.presentation

import com.kimoteru.weathertest.features.weather.presentation.ui.WeatherLogic
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

fun weatherUiModule() = module {
    factory { (coroutineScope: CoroutineScope) -> WeatherLogic(get(), coroutineScope) }
}
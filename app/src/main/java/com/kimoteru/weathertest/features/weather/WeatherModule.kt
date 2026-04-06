package com.kimoteru.weathertest.features.weather

import com.kimoteru.weathertest.features.weather.data.weatherDataModule
import com.kimoteru.weathertest.features.weather.domain.usecase.weatherDomainModule
import com.kimoteru.weathertest.features.weather.presentation.weatherUiModule

fun weatherModule() = weatherDataModule() + weatherDomainModule() + weatherUiModule()
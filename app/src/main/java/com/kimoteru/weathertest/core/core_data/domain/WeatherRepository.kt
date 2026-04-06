package com.kimoteru.weathertest.core.core_data.domain

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel

interface WeatherRepository {
    suspend fun fetchWeather(cityName: String): Result<WeatherModel>
}
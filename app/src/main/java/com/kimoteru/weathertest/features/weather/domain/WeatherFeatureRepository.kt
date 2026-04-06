package com.kimoteru.weathertest.features.weather.domain

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel

interface WeatherFeatureRepository {
    suspend fun getWeather(cityName: String): Result<WeatherModel>
}
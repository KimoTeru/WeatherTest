package com.kimoteru.weathertest.core.core_data.domain.model

data class WeatherModel(
    val cityName: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val humidity: Int,
    val windSpeed: Double,
    val timestamp: Long = System.currentTimeMillis()
)
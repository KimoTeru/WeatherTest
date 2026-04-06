package com.kimoteru.weathertest.core.core_data.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    @SerialName("name") val name: String,
    @SerialName("main") val main: MainDto,
    @SerialName("weather") val weather: List<WeatherDescriptionDto>,
    @SerialName("wind") val wind: WindDto
)

@Serializable
data class MainDto(
    @SerialName("temp") val temp: Double,
    @SerialName("humidity") val humidity: Int
)

@Serializable
data class WeatherDescriptionDto(
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: String
)

@Serializable
data class WindDto(
    @SerialName("speed") val speed: Double
)
package com.kimoteru.weathertest.core.core_data.storage.mapper

import com.kimoteru.weathertest.core.core_data.storage.WeatherEntity
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.core.core_data.network.WeatherDto

fun WeatherDto.toModel(): WeatherModel {
    return WeatherModel(
        cityName = name,
        temperature = main.temp,
        description = weather.firstOrNull()?.description ?: "No description",
        iconUrl = "https://openweathermap.org/img/wn/${weather.firstOrNull()?.icon}@2x.png",
        humidity = main.humidity,
        windSpeed = wind.speed
    )
}

fun WeatherModel.toEntity(): WeatherEntity {
    return WeatherEntity(
        cityName = cityName,
        temperature = temperature,
        description = description,
        iconUrl = iconUrl,
        humidity = humidity,
        windSpeed = windSpeed,
        timestamp = timestamp
    )
}

fun WeatherEntity.toModel(): WeatherModel {
    return WeatherModel(
        cityName = cityName,
        temperature = temperature,
        description = description,
        iconUrl = iconUrl,
        humidity = humidity,
        windSpeed = windSpeed,
        timestamp = timestamp
    )
}
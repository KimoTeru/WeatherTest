package com.kimoteru.weathertest.core.core_data.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_history")
data class WeatherEntity(
    @PrimaryKey val cityName: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val humidity: Int,
    val windSpeed: Double,
    val timestamp: Long
)
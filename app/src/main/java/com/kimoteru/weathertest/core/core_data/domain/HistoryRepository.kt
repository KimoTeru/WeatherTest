package com.kimoteru.weathertest.core.core_data.domain

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun getHistory(): Flow<List<WeatherModel>>
    suspend fun saveWeather(weather: WeatherModel)
    suspend fun deleteByCity(cityName: String)
}

package com.kimoteru.weathertest.core.core_data.storage

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.core.core_data.domain.WeatherRepository
import com.kimoteru.weathertest.core.core_data.network.WeatherApi
import com.kimoteru.weathertest.core.core_data.storage.mapper.toModel

class WeatherRepositoryImpl(
    private val weatherApi: WeatherApi,
    private val apiKey: String
) : WeatherRepository {

    override suspend fun fetchWeather(cityName: String): Result<WeatherModel> {
        return try {
            val dto = weatherApi.getWeather(cityName, apiKey)
            Result.success(dto.toModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
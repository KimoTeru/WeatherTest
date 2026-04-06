package com.kimoteru.weathertest.features.weather.data

import com.kimoteru.weathertest.core.core_data.domain.HistoryRepository
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.core.core_data.domain.WeatherRepository
import com.kimoteru.weathertest.features.weather.domain.WeatherFeatureRepository

class WeatherFeatureRepositoryImpl(
    private val weatherRepository: WeatherRepository,
    private val historyRepository: HistoryRepository
) : WeatherFeatureRepository {

    override suspend fun getWeather(cityName: String): Result<WeatherModel> {
        val result = weatherRepository.fetchWeather(cityName)
        result.onSuccess { weather ->
            historyRepository.deleteByCity(weather.cityName)
            historyRepository.saveWeather(weather)
        }
        return result
    }
}
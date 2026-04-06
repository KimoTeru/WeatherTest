package com.kimoteru.weathertest.features.weather.domain.usecase

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.features.weather.domain.WeatherFeatureRepository

class GetWeatherUseCase(private val repository: WeatherFeatureRepository) {
    suspend operator fun invoke(cityName: String): Result<WeatherModel> {
        return repository.getWeather(cityName)
    }
}
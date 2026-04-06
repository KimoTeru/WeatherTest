package com.kimoteru.weathertest.features.history.domain

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface HistoryFeatureRepository {
    fun getHistory(): Flow<List<WeatherModel>>
}
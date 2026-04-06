package com.kimoteru.weathertest.features.history.data

import com.kimoteru.weathertest.core.core_data.domain.HistoryRepository
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.features.history.domain.HistoryFeatureRepository
import kotlinx.coroutines.flow.Flow

class HistoryFeatureRepositoryImpl(
    private val historyRepository: HistoryRepository
) : HistoryFeatureRepository {

    override fun getHistory(): Flow<List<WeatherModel>> {
        return historyRepository.getHistory()
    }
}
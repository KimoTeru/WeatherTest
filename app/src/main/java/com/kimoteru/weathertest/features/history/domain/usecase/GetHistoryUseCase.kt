package com.kimoteru.weathertest.features.history.domain.usecase

import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.features.history.domain.HistoryFeatureRepository
import kotlinx.coroutines.flow.Flow

class GetHistoryUseCase(private val repository: HistoryFeatureRepository) {
    operator fun invoke(): Flow<List<WeatherModel>> {
        return repository.getHistory()
    }
}

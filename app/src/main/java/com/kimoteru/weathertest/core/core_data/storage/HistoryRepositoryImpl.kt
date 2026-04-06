package com.kimoteru.weathertest.core.core_data.storage

import com.kimoteru.weathertest.core.core_data.domain.HistoryRepository
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.core.core_data.storage.mapper.toEntity
import com.kimoteru.weathertest.core.core_data.storage.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HistoryRepositoryImpl(
    private val dao: WeatherDao
) : HistoryRepository {

    override fun getHistory(): Flow<List<WeatherModel>> {
        return dao.getRecentHistory().map { entities ->
            entities.map { it.toModel() }
        }
    }

    override suspend fun saveWeather(weather: WeatherModel) {
        dao.insertWeather(weather.toEntity())
    }

    override suspend fun deleteByCity(cityName: String) {
        dao.deleteByCity(cityName)
    }
}

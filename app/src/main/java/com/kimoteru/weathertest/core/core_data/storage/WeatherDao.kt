package com.kimoteru.weathertest.core.core_data.storage

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Query("SELECT * FROM weather_history ORDER BY timestamp DESC LIMIT 10")
    fun getRecentHistory(): Flow<List<WeatherEntity>>

    @Query("DELETE FROM weather_history WHERE cityName = :cityName")
    suspend fun deleteByCity(cityName: String)
}
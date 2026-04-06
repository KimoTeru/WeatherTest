package com.kimoteru.weathertest.core.core_data.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherEntity::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract val dao: WeatherDao

    companion object {
        const val NAME = "app_database"
    }
}
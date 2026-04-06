package com.kimoteru.weathertest.features.history.data

import com.kimoteru.weathertest.features.history.domain.HistoryFeatureRepository
import org.koin.dsl.module

fun historyDataModule() = module {
    single<HistoryFeatureRepository> { HistoryFeatureRepositoryImpl(get()) }
}
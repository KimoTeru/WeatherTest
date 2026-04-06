package com.kimoteru.weathertest.features.history.domain.usecase

import org.koin.dsl.module

fun historyDomainModule() = module {
    factory { GetHistoryUseCase(get()) }
}
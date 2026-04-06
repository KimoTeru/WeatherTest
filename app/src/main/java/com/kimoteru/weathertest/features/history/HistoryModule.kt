package com.kimoteru.weathertest.features.history

import com.kimoteru.weathertest.features.history.data.historyDataModule
import com.kimoteru.weathertest.features.history.domain.usecase.historyDomainModule
import com.kimoteru.weathertest.features.history.presentation.historyUiModule

fun historyModule() = historyDataModule() + historyDomainModule() + historyUiModule()
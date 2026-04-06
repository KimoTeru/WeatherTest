package com.kimoteru.weathertest.features.history.presentation

import com.kimoteru.weathertest.features.history.presentation.ui.HistoryLogic
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module

fun historyUiModule() = module {
    factory { (coroutineScope: CoroutineScope) -> HistoryLogic(get(), coroutineScope) }
}

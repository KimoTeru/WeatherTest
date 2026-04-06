package com.kimoteru.weathertest.core.common.base

interface BaseContract {
    interface UiState
    interface UiEvent {
        data object OnBackClick : UiEvent
    }
}
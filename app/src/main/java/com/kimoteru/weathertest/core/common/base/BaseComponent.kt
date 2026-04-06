package com.kimoteru.weathertest.core.common.base

import com.arkivanov.decompose.ComponentContext

interface BaseComponent<S : BaseContract.UiState, E : BaseContract.UiEvent> {
    val logic: BaseLogic<S, E>
    fun onEvent(event: BaseContract.UiEvent)
}

abstract class BaseDefaultComponent<L : BaseLogic<S, E>, S : BaseContract.UiState, E : BaseContract.UiEvent>(
    componentContext: ComponentContext,
    protected val navigateBack: () -> Unit
) : ComponentContext by componentContext, BaseComponent<S, E> {
    abstract override val logic: L

    override fun onEvent(event: BaseContract.UiEvent) {
        when (event) {
            BaseContract.UiEvent.OnBackClick -> navigateBack()
            else -> logic.onEvent(event as E)
        }
    }
}
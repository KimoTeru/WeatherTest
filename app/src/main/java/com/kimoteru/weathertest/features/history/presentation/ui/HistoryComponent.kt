package com.kimoteru.weathertest.features.history.presentation.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.kimoteru.weathertest.core.common.base.BaseComponent
import com.kimoteru.weathertest.core.common.base.BaseContract
import com.kimoteru.weathertest.core.common.base.BaseDefaultComponent
import com.kimoteru.weathertest.core.common.base.BaseLogic
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.features.history.domain.usecase.GetHistoryUseCase
import com.kimoteru.weathertest.features.root_graph.presentation.ui.componentCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

interface HistoryComponent : BaseComponent<HistoryContract.UiState, HistoryContract.UiEvent> {
    override val logic: HistoryLogic
}

class DefaultHistoryComponent(
    componentContext: ComponentContext,
    private val onBack: () -> Unit
) : BaseDefaultComponent<HistoryLogic, HistoryContract.UiState, HistoryContract.UiEvent>(
    componentContext,
    onBack
), HistoryComponent, KoinComponent {

    private val coroutineScope = componentCoroutineScope()
    override val logic: HistoryLogic =
        instanceKeeper.getOrCreate { get { parametersOf(coroutineScope) } }
}

class HistoryLogic(
    private val getHistoryUseCase: GetHistoryUseCase,
    coroutineScope: CoroutineScope
) : BaseLogic<HistoryContract.UiState, HistoryContract.UiEvent>(coroutineScope) {

    private val _uiState = MutableStateFlow(HistoryContract.UiState())
    override val uiState: StateFlow<HistoryContract.UiState> = _uiState.asStateFlow()

    init {
        loadHistory()
    }

    override fun onEvent(event: HistoryContract.UiEvent) {}

    private fun loadHistory() {
        getHistoryUseCase()
            .onEach { history ->
                updateHistoryState(history)
            }
            .launchIn(coroutineScope)
    }

    private fun updateHistoryState(history: List<WeatherModel>) {
        _uiState.update { it.copy(history = history) }
    }
}
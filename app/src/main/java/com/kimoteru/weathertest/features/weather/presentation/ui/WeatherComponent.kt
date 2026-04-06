package com.kimoteru.weathertest.features.weather.presentation.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.kimoteru.weathertest.core.common.base.BaseComponent
import com.kimoteru.weathertest.core.common.base.BaseContract
import com.kimoteru.weathertest.core.common.base.BaseDefaultComponent
import com.kimoteru.weathertest.core.common.base.BaseLogic
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel
import com.kimoteru.weathertest.features.root_graph.presentation.ui.componentCoroutineScope
import com.kimoteru.weathertest.features.weather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.parameter.parametersOf

interface WeatherComponent : BaseComponent<WeatherContract.UiState, WeatherContract.UiEvent> {
    override val logic: WeatherLogic
}

class DefaultWeatherComponent(
    componentContext: ComponentContext,
    private val onHistoryTabClicked: () -> Unit
) : BaseDefaultComponent<WeatherLogic, WeatherContract.UiState, WeatherContract.UiEvent>(
    componentContext,
    onHistoryTabClicked
), WeatherComponent, KoinComponent {

    private val coroutineScope = componentCoroutineScope()
    override val logic: WeatherLogic =
        instanceKeeper.getOrCreate { get { parametersOf(coroutineScope) } }

    override fun onEvent(event: BaseContract.UiEvent) {
        when (event) {
            WeatherContract.UiEvent.OnHistory -> onHistoryTabClicked()
            else -> super.onEvent(event)
        }
    }
}

class WeatherLogic(
    private val getWeatherUseCase: GetWeatherUseCase,
    coroutineScope: CoroutineScope
) : BaseLogic<WeatherContract.UiState, WeatherContract.UiEvent>(coroutineScope) {

    private val _uiState = MutableStateFlow(WeatherContract.UiState())
    override val uiState: StateFlow<WeatherContract.UiState> = _uiState.asStateFlow()

    override fun onEvent(event: WeatherContract.UiEvent) {
        when (event) {
            is WeatherContract.UiEvent.OnCityNameChange -> updateCityNameState(event.cityName)
            is WeatherContract.UiEvent.OnSearch -> searchWeather(event.city)
            else -> {}
        }
    }

    private fun searchWeather(city: String) {
        coroutineScope.launch {
            updateIsLoadingState(true)
            updateErrorState(null)

            getWeatherUseCase(city)
                .onSuccess { weather ->
                    updateWeatherState(weather)
                    updateIsLoadingState(false)
                }
                .onFailure { error ->
                    updateIsLoadingState(false)
                    updateErrorState(error.message)
                }
        }
    }

    private fun updateWeatherState(weather: WeatherModel?) {
        _uiState.update { it.copy(weather = weather) }
    }

    private fun updateIsLoadingState(isLoading: Boolean) {
        _uiState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateErrorState(error: String?) {
        _uiState.update { it.copy(error = error) }
    }

    private fun updateCityNameState(cityName: String) {
        _uiState.update { it.copy(cityName = cityName) }
    }
}

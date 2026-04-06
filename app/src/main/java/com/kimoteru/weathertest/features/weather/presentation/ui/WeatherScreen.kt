package com.kimoteru.weathertest.features.weather.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.kimoteru.weathertest.R
import coil3.compose.AsyncImage
import com.kimoteru.weathertest.core.common.base.BaseContract
import com.kimoteru.weathertest.core.core_data.domain.model.WeatherModel

interface WeatherContract : BaseContract {
    data class UiState(
        val cityName: String = "",
        val weather: WeatherModel? = null,
        val isLoading: Boolean = false,
        val error: String? = null
    ) : BaseContract.UiState

    sealed interface UiEvent : BaseContract.UiEvent {
        data class OnCityNameChange(val cityName: String) : UiEvent
        data class OnSearch(val city: String) : UiEvent
        data object OnHistory : UiEvent
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    component: WeatherComponent,
    modifier: Modifier = Modifier
) {
    val state by component.logic.uiState.collectAsState()
    val onEvent = component::onEvent
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.weather_title),
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = { onEvent(WeatherContract.UiEvent.OnHistory) }) {
                        Icon(
                            Icons.Default.History,
                            contentDescription = stringResource(R.string.history_content_description)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.cityName,
                onValueChange = { onEvent(WeatherContract.UiEvent.OnCityNameChange(it)) },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(stringResource(R.string.city_name_label)) },
                trailingIcon = {
                    IconButton(onClick = {
                        onEvent(WeatherContract.UiEvent.OnSearch(state.cityName))
                        focusManager.clearFocus()
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = stringResource(R.string.search_content_description)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onEvent(WeatherContract.UiEvent.OnSearch(state.cityName))
                    focusManager.clearFocus()
                })
            )

            Spacer(modifier = Modifier.height(24.dp))

            when {
                state.isLoading -> CircularProgressIndicator()
                state.error != null -> Text(
                    text = state.error!!,
                    color = MaterialTheme.colorScheme.error
                )

                state.weather != null -> WeatherCard(state.weather!!)
                else -> Text(text = stringResource(R.string.search_placeholder))
            }
        }
    }
}

@Composable
private fun WeatherCard(weather: WeatherModel) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = weather.cityName, style = MaterialTheme.typography.headlineMedium)

            AsyncImage(
                model = weather.iconUrl,
                contentDescription = null,
                modifier = Modifier.size(120.dp)
            )

            Text(
                text = stringResource(R.string.temperature_unit, weather.temperature.toInt()),
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = weather.description.replaceFirstChar { it.uppercase() },
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                WeatherInfoItem(
                    stringResource(R.string.humidity_label),
                    stringResource(R.string.humidity_unit, weather.humidity)
                )

                WeatherInfoItem(
                    stringResource(R.string.wind_label),
                    stringResource(R.string.wind_unit, weather.windSpeed.toString())
                )
            }
        }
    }
}

@Composable
private fun WeatherInfoItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelMedium)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold
        )
    }
}

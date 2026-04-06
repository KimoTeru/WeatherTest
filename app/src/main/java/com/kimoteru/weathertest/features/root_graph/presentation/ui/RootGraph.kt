package com.kimoteru.weathertest.features.root_graph.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.kimoteru.weathertest.features.history.presentation.ui.HistoryScreen
import com.kimoteru.weathertest.features.weather.presentation.ui.WeatherScreen

@Composable
fun RootGraph(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(fade()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.WeatherChild -> WeatherScreen(component = child.component)
            is RootComponent.Child.HistoryChild -> HistoryScreen(component = child.component)
        }
    }
}
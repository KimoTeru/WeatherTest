package com.kimoteru.weathertest.features.root_graph.presentation.ui

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.bringToFront
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.lifecycle.doOnDestroy
import com.kimoteru.weathertest.features.history.presentation.ui.DefaultHistoryComponent
import com.kimoteru.weathertest.features.history.presentation.ui.HistoryComponent
import com.kimoteru.weathertest.features.weather.presentation.ui.DefaultWeatherComponent
import com.kimoteru.weathertest.features.weather.presentation.ui.WeatherComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.serialization.Serializable
import org.koin.core.component.KoinComponent

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    fun onBackClicked()

    sealed class Child {
        class WeatherChild(val component: WeatherComponent) : Child()
        class HistoryChild(val component: HistoryComponent) : Child()
    }
}

class DefaultRootComponent(
    componentContext: ComponentContext,
) : RootComponent, ComponentContext by componentContext, KoinComponent {

    private val navigation = StackNavigation<Config>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Config.serializer(),
            initialConfiguration = Config.Weather,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    private fun createChild(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child =
        when (config) {
            Config.Weather -> RootComponent.Child.WeatherChild(
                DefaultWeatherComponent(
                    componentContext = componentContext,
                    onHistoryTabClicked = {
                        navigation.bringToFront(Config.History)
                    }
                )
            )

            Config.History -> RootComponent.Child.HistoryChild(
                DefaultHistoryComponent(
                    componentContext = componentContext,
                    onBack = {
                        navigation.pop()
                    }
                )
            )
        }

    override fun onBackClicked() {
        navigation.pop()
    }

    @Serializable
    private sealed interface Config {
        @Serializable
        data object Weather : Config

        @Serializable
        data object History : Config
    }
}

fun ComponentContext.componentCoroutineScope(): CoroutineScope {
    val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    lifecycle.doOnDestroy {
        coroutineScope.cancel()
    }
    return coroutineScope
}

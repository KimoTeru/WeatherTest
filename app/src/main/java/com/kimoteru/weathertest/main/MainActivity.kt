package com.kimoteru.weathertest.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.defaultComponentContext
import com.kimoteru.weathertest.features.root_graph.presentation.ui.DefaultRootComponent
import com.kimoteru.weathertest.features.root_graph.presentation.ui.RootGraph
import com.kimoteru.weathertest.core.design_system.theme.WeatherTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val root = DefaultRootComponent(componentContext = defaultComponentContext())

        setContent {
            WeatherTestTheme {
                RootGraph(
                    component = root,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
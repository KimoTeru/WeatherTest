package com.kimoteru.weathertest.features

import com.kimoteru.weathertest.features.history.historyModule
import com.kimoteru.weathertest.features.weather.weatherModule

fun featuresModule() = weatherModule() + historyModule()
package com.kimoteru.weathertest.core

import com.kimoteru.weathertest.core.common.commonModule
import com.kimoteru.weathertest.core.core_data.networkModule
import com.kimoteru.weathertest.core.core_data.repositoryModule
import com.kimoteru.weathertest.core.core_data.storageModule

fun coreModule() = networkModule() + storageModule() + repositoryModule() + commonModule()
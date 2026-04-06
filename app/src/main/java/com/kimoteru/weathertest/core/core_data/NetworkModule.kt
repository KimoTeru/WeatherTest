package com.kimoteru.weathertest.core.core_data

import com.kimoteru.weathertest.BuildConfig
import com.kimoteru.weathertest.core.core_data.network.WeatherApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

fun networkModule() = module {
    single {
        Json {
            ignoreUnknownKeys = true
            coerceInputValues = true
        }
    }

    single(named(NetworkQualifiers.DEFAULT_CLIENT)) {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        okHttpClient.addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        okHttpClient.build()
    }

    single(named(NetworkQualifiers.DEFAULT_RETROFIT)) {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get(named(NetworkQualifiers.DEFAULT_CLIENT)))
            .addConverterFactory(
                get<Json>().asConverterFactory("application/json".toMediaType())
            )
            .build()
    }

    single {
        get<Retrofit>(named(NetworkQualifiers.DEFAULT_RETROFIT)).create(WeatherApi::class.java)
    }
}

object NetworkQualifiers {
    const val DEFAULT_CLIENT = "default_client"
    const val DEFAULT_RETROFIT = "default_retrofit"
}
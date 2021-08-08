package com.hamiddev.weather.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import timber.log.Timber

fun <T> Flow<T>.handleException(): Flow<T> =
    catch { e -> Timber.i("ssdss -> ${e.message}") }

fun weatherIconLink(name: String) =
    "https://openweathermap.org/img/wn/$name@2x.png"

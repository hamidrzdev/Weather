package com.hamiddev.weather.data.repo

import com.hamiddev.weather.model.server.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

interface WeatherRepository {
    fun getWeather(latLng: LatLng): Flow<WeatherResponse>
}
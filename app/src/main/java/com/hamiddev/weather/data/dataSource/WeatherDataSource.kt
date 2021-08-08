package com.hamiddev.weather.data.dataSource

import com.hamiddev.weather.model.server.WeatherResponse
import org.neshan.common.model.LatLng

interface WeatherDataSource {
    suspend fun getWeather(latLng: LatLng): WeatherResponse
}
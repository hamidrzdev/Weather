package com.hamiddev.weather.data.dataSource

import com.hamiddev.weather.model.WeatherResponse
import org.neshan.common.model.LatLng

interface WeatherDataSource {
    suspend fun getWeather(latLng: LatLng): WeatherResponse
}
package com.hamiddev.weather.data.repo

import com.hamiddev.weather.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

interface WeatherRepository {
    fun getWeather(latLng: LatLng): Flow<WeatherResponse>
}
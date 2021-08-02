package com.hamiddev.data

import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

interface WeatherDataSource {
    fun getWeather(latLng: LatLng, accessToken: String): Flow<WeatherResponse>
}
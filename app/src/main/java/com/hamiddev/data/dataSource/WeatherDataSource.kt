package com.hamiddev.data.dataSource

import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

interface WeatherDataSource {
    suspend fun getWeather(latLng: LatLng, accessToken: String): WeatherResponse
}
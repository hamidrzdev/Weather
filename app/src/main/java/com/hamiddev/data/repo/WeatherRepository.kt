package com.hamiddev.data.repo

import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

interface WeatherRepository {
    fun getWeather(latLng: LatLng): Flow<WeatherResponse>
}
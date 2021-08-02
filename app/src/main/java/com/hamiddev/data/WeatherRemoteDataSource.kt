package com.hamiddev.data

import com.hamiddev.model.WeatherResponse
import com.hamiddev.weather.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.neshan.common.model.LatLng

class WeatherRemoteDataSource(private val apiService: ApiService) : WeatherDataSource {
    override fun getWeather(latLng: LatLng, accessToken: String): Flow<WeatherResponse> =
        apiService.getWeather(
            latLng.latitude.toString(),
            latLng.longitude.toString(),
            "minutely,alerts",
            "fa",
            accessToken
        )
}
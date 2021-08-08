package com.hamiddev.weather.data.dataSource

import com.hamiddev.weather.model.WeatherResponse
import com.hamiddev.weather.ACCESS_TOKEN
import com.hamiddev.weather.ApiService
import org.neshan.common.model.LatLng
import javax.inject.Inject

class WeatherRemoteDataSource @Inject constructor(private val apiService: ApiService) : WeatherDataSource {
    override suspend fun getWeather(latLng: LatLng): WeatherResponse =
            apiService.getWeather(
                latLng.latitude,
                latLng.longitude,
                "minutely,alerts",
                "fa",
                ACCESS_TOKEN
            )
}
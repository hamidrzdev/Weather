package com.hamiddev.data.repo

import com.hamiddev.data.dataSource.WeatherDataSource
import com.hamiddev.data.dataSource.WeatherRemoteDataSource
import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import org.neshan.common.model.LatLng

class WeatherRepositoryImpl(private val weatherRemoteDataSource: WeatherDataSource) :
    WeatherRepository {
    override fun getWeather(latLng: LatLng, accessToken: String): Flow<WeatherResponse> =
        weatherRemoteDataSource.getWeather(latLng,accessToken)
}
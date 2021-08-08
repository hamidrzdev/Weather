package com.hamiddev.weather.data.repo

import com.hamiddev.weather.data.dataSource.WeatherDataSource
import com.hamiddev.weather.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.neshan.common.model.LatLng
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherRemoteDataSource: WeatherDataSource) :
    WeatherRepository {

    override fun getWeather(latLng: LatLng): Flow<WeatherResponse> {
        return flow {
            emit(weatherRemoteDataSource.getWeather(latLng))
        }.flowOn(Dispatchers.IO)

    }
}
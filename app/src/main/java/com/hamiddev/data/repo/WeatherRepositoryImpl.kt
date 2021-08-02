package com.hamiddev.data.repo

import android.widget.Toast
import com.hamiddev.data.dataSource.WeatherDataSource
import com.hamiddev.data.dataSource.WeatherRemoteDataSource
import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import org.neshan.common.model.LatLng
import timber.log.Timber

class WeatherRepositoryImpl(private val weatherRemoteDataSource: WeatherDataSource) :
    WeatherRepository {
    override fun getWeather(latLng: LatLng, accessToken: String): Flow<WeatherResponse> =
        weatherRemoteDataSource.getWeather(latLng, accessToken)
            .flowOn(Dispatchers.IO)
}
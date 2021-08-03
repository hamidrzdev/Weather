package com.hamiddev.data.repo

import android.widget.Toast
import com.hamiddev.data.dataSource.WeatherDataSource
import com.hamiddev.data.dataSource.WeatherRemoteDataSource
import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.neshan.common.model.LatLng
import timber.log.Timber
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val weatherRemoteDataSource: WeatherDataSource) :
    WeatherRepository {

    override fun getWeather(latLng: LatLng, accessToken: String): Flow<WeatherResponse> {
        return flow {
            emit(weatherRemoteDataSource.getWeather(latLng,accessToken))
        }.flowOn(Dispatchers.IO)

    }
}
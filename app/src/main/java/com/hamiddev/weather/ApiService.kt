package com.hamiddev.weather

import com.hamiddev.model.WeatherResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("onecall")
    fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("lang") lang: String,
        @Query("appid") accessToken: String
    ): Flow<WeatherResponse>

}
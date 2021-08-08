package com.hamiddev.weather

import com.hamiddev.weather.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("onecall")
    suspend fun getWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("exclude") exclude: String,
        @Query("lang") lang: String,
        @Query("appid") accessToken: String
    ): WeatherResponse

}
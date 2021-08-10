package com.hamiddev.weather.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.sql.Timestamp

@Parcelize
data class DetailWeather(
    val timestamp: Long,
    val image:String,
    val minTemp: Int,
    val maxTemp: Int,
    val sunrise: Int,
    val sunset: Int,
    val windSpeed: Double,
    val uv: Double,
    val rain: Double
):Parcelable
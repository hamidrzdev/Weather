package com.hamiddev.weather.model

import java.sql.Timestamp

data class DayWeather(
    val timestamp: Long,
    val image:String,
    val minTemp:String,
    val maxTemp:String
)
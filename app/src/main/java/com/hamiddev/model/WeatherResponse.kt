package com.hamiddev.model

data class WeatherResponse(
    val current: Current,
    val daily: List<Daily>,
    val hourly: List<Hourly>,
    val lat: Int,
    val lon: Int,
    val timezone: String,
    val timezone_offset: Int
)
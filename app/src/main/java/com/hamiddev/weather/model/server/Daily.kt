package com.hamiddev.weather.model.server

import com.hamiddev.weather.model.DayWeather
import com.hamiddev.weather.model.DetailWeather
import com.hamiddev.weather.model.HourWeather

data class Daily(
    val clouds: Int,
    val dew_point: Double,
    val dt: Long,
    val feels_like: FeelsLike,
    val humidity: Int,
    val moon_phase: Double,
    val moonrise: Int,
    val moonset: Int,
    val pop: Double,
    val pressure: Int,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    val uvi: Double,
    val weather: List<Weather>,
    val wind_deg: Int,
    val wind_gust: Double,
    val wind_speed: Double
) {
    fun toDayWeather() =
        DayWeather(dt, weather[0].icon, temp.min.toString(), temp.max.toString())

    fun toDetailWeather() =
        DetailWeather(dt, weather[0].icon ,temp.min.toInt(), temp.max.toInt(), sunrise, sunset, wind_speed, uvi, pop)
}
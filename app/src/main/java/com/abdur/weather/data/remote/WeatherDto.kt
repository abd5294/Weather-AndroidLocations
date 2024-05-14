package com.abdur.weather.data.remote

import com.squareup.moshi.Json
import retrofit2.http.Path

data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData : WeatherDataDto
)
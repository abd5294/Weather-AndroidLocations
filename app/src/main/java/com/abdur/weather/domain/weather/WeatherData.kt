package com.abdur.weather.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time : LocalDateTime,
    val temperature : Double,
    val weatherType: WeatherType,
    val humidity : Int,
    val windSpeed : Double,
    val pressure : Double
)
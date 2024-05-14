package com.abdur.weather.presentation

import com.abdur.weather.domain.weather.WeatherInfo

data class WeatherState(
    val weatherInfo : WeatherInfo? = null,
    val isLoading : Boolean? = null,
    val error : String? = null
)
package com.abdur.weather.domain.weather

data class WeatherInfo(
    val weatherDataPerDay : Map<Int, List<WeatherData>>,
    val currentWeather : WeatherData?
)
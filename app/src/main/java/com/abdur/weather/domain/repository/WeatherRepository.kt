package com.abdur.weather.domain.repository

import com.abdur.weather.domain.util.Resource
import com.abdur.weather.domain.weather.WeatherData
import com.abdur.weather.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat : Double, long : Double) : Resource<WeatherInfo>
}
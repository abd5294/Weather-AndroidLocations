package com.abdur.weather.data.remote

import com.abdur.weather.domain.weather.WeatherInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    @GET("v1/forecast?&hourly=temperature_2m,weathercode,relativehumidity_2m,windspeed_10m,pressure_msl")
    suspend fun getWeatherData(
        @Query("latitude") lat : Double,
        @Query("longitude") long : Double
    ) : WeatherDto

    companion object{
        const val BASE_URL = "https://api.open-meteo.com/"
    }
}
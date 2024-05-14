package com.abdur.weather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.abdur.weather.data.mappers.toWeatherInfo
import com.abdur.weather.data.remote.WeatherApi
import com.abdur.weather.domain.repository.WeatherRepository
import com.abdur.weather.domain.util.Resource
import com.abdur.weather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api : WeatherApi
): WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, long).toWeatherInfo()
            )
        } catch (e : Exception){
            Resource.Error("An Error occurred")
        }
    }
}
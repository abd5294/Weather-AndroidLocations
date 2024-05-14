package com.abdur.weather.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.abdur.weather.data.remote.WeatherDataDto
import com.abdur.weather.data.remote.WeatherDto
import com.abdur.weather.domain.weather.WeatherData
import com.abdur.weather.domain.weather.WeatherInfo
import com.abdur.weather.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData,
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {

    return time.mapIndexed { index, time ->
        val temperature = temperature[index]
        val pressure = pressure[index]
        val weatherCode = weatherCode[index]
        val windSpeed = windSpeed[index]
        val humidity = humidity[index]
        IndexedWeatherData(
            index = index,
            weatherData = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperature = temperature,
                weatherType = WeatherType.fromWMO(weatherCode),
                humidity = humidity,
                windSpeed = windSpeed,
                pressure = pressure
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { it ->
        it.value.map { it.weatherData }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDto.toWeatherInfo() : WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }

    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeather = currentWeatherData
    )
}
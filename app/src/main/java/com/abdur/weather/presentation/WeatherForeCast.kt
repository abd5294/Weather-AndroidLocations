package com.abdur.weather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForeCast(
    state: WeatherState,
    modifier: Modifier = Modifier,
) {
    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Today",
                style = TextStyle(fontSize = 20.sp, color = Color.White)
            )
            Spacer(modifier = modifier.height(16.dp))
            LazyRow {
                items(data) { weatherData ->
                    HourlyWeatherDisplay(
                        modifier = modifier,
                        weatherData = weatherData,
                        textColor = Color.White
                    )
                    Spacer(modifier = modifier.width(12.dp))
                }
            }
        }
    }
}
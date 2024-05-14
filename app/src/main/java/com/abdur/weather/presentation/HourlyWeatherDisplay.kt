package com.abdur.weather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abdur.weather.domain.weather.WeatherData
import com.abdur.weather.presentation.WeatherState
import com.abdur.weather.presentation.ui.theme.DeepBlue
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyWeatherDisplay(
    modifier: Modifier = Modifier,
    weatherData: WeatherData,
    textColor: Color = Color.White,
) {

    val formattedDateTime = remember(weatherData) {
        weatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }

    Column(
        modifier = modifier
            .height(100.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(DeepBlue)
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = formattedDateTime, color = Color.LightGray)
        Image(
            painter = painterResource(id = weatherData.weatherType.iconRes),
            contentDescription = "Weather Type Image",
            modifier = modifier.width(40.dp)
        )
        Text(
            text = "${weatherData.temperature}°C",
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}
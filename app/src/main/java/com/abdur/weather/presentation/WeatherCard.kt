package com.abdur.weather.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abdur.weather.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherCard(
    weatherState: WeatherState,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
) {

    weatherState.weatherInfo?.currentWeather?.let { data ->
        Card(
            colors = CardDefaults.cardColors(containerColor = backgroundColor),
            shape = RoundedCornerShape(10.dp),
            modifier = modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Today ${
                        data.time.format(
                            DateTimeFormatter.ofPattern("HH:mm")
                        )
                    }", modifier = modifier.align(Alignment.End),
                    color = Color.White
                )
                Spacer(modifier = modifier.height(16.dp))
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    modifier = modifier
                        .width(200.dp)
                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = "${data.temperature}°C",
                    style = TextStyle(fontSize = 50.sp, color = Color.White),

                )
                Spacer(modifier = modifier.height(16.dp))
                Text(
                    text = data.weatherType.weatherDesc,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.White
                    ),
                )
                Spacer(modifier = modifier.height(32.dp))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    WeatherDataDisplay(
                        value = data.pressure.toInt(),
                        unit = "hpa",
                        icon = ImageVector.vectorResource(R.drawable.ic_pressure),
                        textStyle = TextStyle(Color.White),
                        iconTint = Color.White
                    )
                    WeatherDataDisplay(
                        value = data.humidity,
                        unit = "%",
                        icon = ImageVector.vectorResource(R.drawable.ic_drop),
                        textStyle = TextStyle(Color.White),
                        iconTint = Color.White
                    )
                    WeatherDataDisplay(
                        value = data.windSpeed.toInt(),
                        unit = "km/h",
                        icon = ImageVector.vectorResource(R.drawable.ic_wind),
                        textStyle = TextStyle(Color.White),
                        iconTint = Color.White
                    )
                }

            }
        }
    }
}
package com.abdur.weather

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abdur.weather.presentation.WeatherCard
import com.abdur.weather.presentation.WeatherForeCast
import com.abdur.weather.presentation.WeatherViewModel
import com.abdur.weather.presentation.ui.theme.DarkBlue
import com.abdur.weather.presentation.ui.theme.DeepBlue
import com.abdur.weather.presentation.ui.theme.WeatherTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    val viewmodel: WeatherViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewmodel.loadWeatherData()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

        setContent {
            WeatherTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DarkBlue)
                ) {
                    WeatherCard(weatherState = viewmodel.state, backgroundColor = DeepBlue)
                    Spacer(modifier = Modifier.height(16.dp))
                    WeatherForeCast(state = viewmodel.state)
                }
            }
        }
    }
}

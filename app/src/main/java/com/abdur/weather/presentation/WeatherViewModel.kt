package com.abdur.weather.presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdur.weather.data.location.DefaultLocationTracker
import com.abdur.weather.domain.location.LocationTracker
import com.abdur.weather.domain.repository.WeatherRepository
import com.abdur.weather.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository : WeatherRepository,
    private val locationTracker: LocationTracker
) : ViewModel() {

    var state by mutableStateOf(WeatherState())
        private set

    fun loadWeatherData(){
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
                error = null
            )
            locationTracker.getCurrentLocation()?.let {
                when(val result = repository.getWeatherData(it.latitude, it.longitude)){
                    is Resource.Error -> {
                        state = state.copy(
                            weatherInfo = null,
                            isLoading = false,
                            error = "An error occurred. try again later"
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            weatherInfo = result.data,
                            isLoading = false,
                            error = null
                        )
                    }
                }
            } ?: run {
                state = state.copy(
                    isLoading = false,
                    error = "Unable to fetch the location. Make sure to check for the permissions."
                )
            }
        }
    }
}
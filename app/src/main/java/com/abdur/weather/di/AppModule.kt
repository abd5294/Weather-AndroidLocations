package com.abdur.weather.di

import android.app.Application
import com.abdur.weather.data.remote.WeatherApi
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(WeatherApi.BASE_URL)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideFusedLocationProviderClient(application : Application) : FusedLocationProviderClient{
        return LocationServices.getFusedLocationProviderClient(application)
    }
}
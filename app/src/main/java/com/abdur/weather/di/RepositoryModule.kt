package com.abdur.weather.di

import com.abdur.weather.data.location.DefaultLocationTracker
import com.abdur.weather.data.repository.WeatherRepositoryImpl
import com.abdur.weather.domain.location.LocationTracker
import com.abdur.weather.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryModule(weatherRepositoryImpl: WeatherRepositoryImpl) : WeatherRepository
}
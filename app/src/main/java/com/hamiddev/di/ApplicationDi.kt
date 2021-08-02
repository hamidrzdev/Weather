package com.hamiddev.di

import com.hamiddev.data.dataSource.WeatherRemoteDataSource
import com.hamiddev.data.repo.WeatherRepository
import com.hamiddev.data.repo.WeatherRepositoryImpl
import com.hamiddev.weather.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationDi {

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: ApiService): WeatherRepository =
        WeatherRepositoryImpl(WeatherRemoteDataSource(apiService))
}
package com.hamiddev.weather.di

import com.hamiddev.weather.data.dataSource.WeatherRemoteDataSource
import com.hamiddev.weather.data.repo.WeatherRepository
import com.hamiddev.weather.data.repo.WeatherRepositoryImpl
import com.hamiddev.weather.ApiService
import com.hamiddev.weather.service.FrescoImageLoadingServiceImpl
import com.hamiddev.weather.service.ImageLoadingService
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

    @Provides
    @Singleton
    fun provideImageLoadingService(): ImageLoadingService =
        FrescoImageLoadingServiceImpl()
}
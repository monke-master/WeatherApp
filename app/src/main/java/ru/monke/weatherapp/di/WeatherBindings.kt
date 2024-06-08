package ru.monke.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.monke.weatherapp.data.weather.WeatherRemoteDataSourceImpl
import ru.monke.weatherapp.data.weather.WeatherRepositoryImpl
import ru.monke.weatherapp.domain.weather.WeatherRemoteDataSource
import ru.monke.weatherapp.domain.weather.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
interface WeatherBindings {

    @Binds
    fun bindWeatherRepository(i: WeatherRepositoryImpl): WeatherRepository

    @Binds
    fun bindWeatherRemoteDataSource(i: WeatherRemoteDataSourceImpl): WeatherRemoteDataSource
}
package ru.monke.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.monke.weatherapp.data.CityRemoteDataSourceImpl
import ru.monke.weatherapp.data.CityRepositoryImpl
import ru.monke.weatherapp.domain.CityRemoteDataSource
import ru.monke.weatherapp.domain.CityRepository

@InstallIn(SingletonComponent::class)
@Module
interface CityModule {

    @Binds
    fun bindCityRepository(i: CityRepositoryImpl): CityRepository

    @Binds
    fun bindCityRemoteDataSource(i: CityRemoteDataSourceImpl): CityRemoteDataSource
}
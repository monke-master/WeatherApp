package ru.monke.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.monke.weatherapp.data.city.CityRemoteDataSourceImpl
import ru.monke.weatherapp.data.city.CityRepositoryImpl
import ru.monke.weatherapp.domain.city.CityRemoteDataSource
import ru.monke.weatherapp.domain.city.CityRepository

@Module
@InstallIn(SingletonComponent::class)
interface CityBindings {

    @Binds
    fun bindCityRepository(i: CityRepositoryImpl): CityRepository

    @Binds
    fun bindCityRemoteDataSource(i: CityRemoteDataSourceImpl): CityRemoteDataSource
}
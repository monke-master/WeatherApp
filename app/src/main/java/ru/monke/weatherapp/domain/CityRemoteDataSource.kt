package ru.monke.weatherapp.domain

interface CityRemoteDataSource {

    suspend fun getCitiesList(): Result<List<City>>
}
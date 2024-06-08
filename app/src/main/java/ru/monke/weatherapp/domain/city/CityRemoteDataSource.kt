package ru.monke.weatherapp.domain.city

interface CityRemoteDataSource {

    suspend fun getCitiesList(): Result<List<City>>
}
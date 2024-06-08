package ru.monke.weatherapp.domain.city

interface CityRepository {

    suspend fun getCitiesList(): Result<List<City>>

}
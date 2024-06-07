package ru.monke.weatherapp.domain

interface CityRepository {

    suspend fun getCitiesList(): Result<List<City>>

}
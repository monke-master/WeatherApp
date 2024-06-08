package ru.monke.weatherapp.data.city

import retrofit2.http.GET

interface CityApi {

    @GET("cities.json")
    suspend fun getCitiesList(): List<CityRemote>
}
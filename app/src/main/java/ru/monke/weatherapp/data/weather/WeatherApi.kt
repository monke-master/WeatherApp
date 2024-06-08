package ru.monke.weatherapp.data.weather

import retrofit2.http.GET
import retrofit2.http.Query
import ru.monke.weatherapp.BuildConfig

interface WeatherApi {

    @GET("?exclude=minutely%2Chourly%2Cdaily%2Calerts&units=metric&appid=${BuildConfig.AUTH_KEY}")
    suspend fun getWeatherByCords(
        @Query("lat") latitude: Float,
        @Query("lon") longitude: Float,
    ): WeatherResponse
}
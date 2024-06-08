package ru.monke.weatherapp.domain.weather

interface WeatherRepository {

    suspend fun getWeatherByCords(longitude: Float, latitude: Float): Result<Weather>
}
package ru.monke.weatherapp.data.weather

import ru.monke.weatherapp.domain.weather.Weather
import ru.monke.weatherapp.domain.weather.WeatherRemoteDataSource
import javax.inject.Inject

class WeatherRemoteDataSourceImpl @Inject constructor(
    private val weatherApi: WeatherApi
): WeatherRemoteDataSource {

    override suspend fun getWeatherByCords(longitude: Float, latitude: Float): Result<Weather> {
        return try {
            val response = weatherApi.getWeatherByCords(latitude = latitude, longitude = longitude)
            Result.success(response.main.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
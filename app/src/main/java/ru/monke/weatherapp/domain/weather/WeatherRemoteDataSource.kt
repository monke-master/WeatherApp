package ru.monke.weatherapp.domain.weather

interface WeatherRemoteDataSource {
    suspend fun getWeatherByCords(longitude: Float, latitude: Float): Result<Weather>
}
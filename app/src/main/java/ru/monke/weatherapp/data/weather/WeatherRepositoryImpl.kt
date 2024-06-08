package ru.monke.weatherapp.data.weather

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.monke.weatherapp.domain.weather.Weather
import ru.monke.weatherapp.domain.weather.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteSource: WeatherRemoteDataSourceImpl
): WeatherRepository {

    override suspend fun getWeatherByCords(longitude: Float, latitude: Float): Result<Weather> {
        return withContext(Dispatchers.IO) {
            remoteSource.getWeatherByCords(longitude = longitude, latitude = latitude)
        }
    }
}
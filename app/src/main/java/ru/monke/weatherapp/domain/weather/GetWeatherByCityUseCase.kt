package ru.monke.weatherapp.domain.weather

import ru.monke.weatherapp.domain.city.City
import javax.inject.Inject

class GetWeatherByCityUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun execute(city: City): Result<Weather> {
        return weatherRepository.getWeatherByCords(
            longitude = city.longitude,
            latitude = city.latitude)
    }
}
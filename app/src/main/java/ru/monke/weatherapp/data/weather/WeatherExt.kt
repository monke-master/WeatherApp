package ru.monke.weatherapp.data.weather

import ru.monke.weatherapp.domain.weather.Weather

fun WeatherRemote.toDomain() =
    Weather(temp = temp)
package ru.monke.weatherapp.data.city

import ru.monke.weatherapp.domain.city.City

fun CityRemote.toDomain(): City =
    City(
        name = city,
        id = id,
        longitude = longitude,
        latitude = latitude
    )
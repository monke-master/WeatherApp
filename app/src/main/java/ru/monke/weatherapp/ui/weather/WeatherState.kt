package ru.monke.weatherapp.ui.weather

import ru.monke.weatherapp.domain.weather.Weather

data class WeatherState(
    val weather: Weather? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
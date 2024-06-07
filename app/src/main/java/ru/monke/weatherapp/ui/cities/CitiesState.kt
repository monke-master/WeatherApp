package ru.monke.weatherapp.ui.cities

import ru.monke.weatherapp.domain.City

data class CitiesState(
    val citiesList: List<City> = emptyList(),
    val errorMessage: String? = null,
    val isLoading: Boolean = false
)
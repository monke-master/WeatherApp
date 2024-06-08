package ru.monke.weatherapp.domain.city

data class City(
    val id: String,
    val name: String,
    val longitude: Float,
    val latitude: Float,
)
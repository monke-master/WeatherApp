package ru.monke.weatherapp.ui.cities

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import ru.monke.weatherapp.domain.City
import ru.monke.weatherapp.domain.mockedCities

class CityParameterProvider: PreviewParameterProvider<City> {
    override val values = sequenceOf(
        City("Домодедово")
    )
}

class CitiesListParameterProvider: PreviewParameterProvider<List<City>> {
    override val values = sequenceOf(
        mockedCities
    )
}
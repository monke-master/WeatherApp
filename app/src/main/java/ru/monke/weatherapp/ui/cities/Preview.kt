package ru.monke.weatherapp.ui.cities

import ru.monke.weatherapp.domain.City
import ru.monke.weatherapp.domain.CityRepository
import ru.monke.weatherapp.domain.mockedCities

class FakeRepository(
): CityRepository {


    override suspend fun getCitiesList(): Result<List<City>> {
        return Result.success(mockedCities)
    }

}

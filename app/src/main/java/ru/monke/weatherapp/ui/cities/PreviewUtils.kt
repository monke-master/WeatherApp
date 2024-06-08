package ru.monke.weatherapp.ui.cities

import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.city.CityRepository
import ru.monke.weatherapp.domain.mockedCities

class FakeRepository(
): CityRepository {


    override suspend fun getCitiesList(): Result<List<City>> {
        return Result.success(mockedCities)
    }

}

package ru.monke.weatherapp.data

import ru.monke.weatherapp.domain.City
import ru.monke.weatherapp.domain.CityRemoteDataSource
import ru.monke.weatherapp.domain.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource
): CityRepository {

    override suspend fun getCitiesList(): Result<List<City>> {
        return remoteDataSource.getCitiesList()
    }

}

fun CityRemote.toDomain(): City = City(city)
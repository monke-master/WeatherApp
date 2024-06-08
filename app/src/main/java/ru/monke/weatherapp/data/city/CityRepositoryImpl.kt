package ru.monke.weatherapp.data.city

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.city.CityRemoteDataSource
import ru.monke.weatherapp.domain.city.CityRepository
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val remoteDataSource: CityRemoteDataSource
): CityRepository {

    override suspend fun getCitiesList(): Result<List<City>> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.getCitiesList()
        }
    }
}
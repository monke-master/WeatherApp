package ru.monke.weatherapp.data.city

import android.util.Log
import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.city.CityRemoteDataSource
import javax.inject.Inject

private const val TAG = "CityRemoteDataSourceImpl"

class CityRemoteDataSourceImpl @Inject constructor(
    private val api: CityApi
): CityRemoteDataSource {

    override suspend fun getCitiesList(): Result<List<City>> {
        return try {
            val list = api.getCitiesList()
            Result.success(list.map { it.toDomain() })
        } catch (e: Exception) {
            Log.d(TAG, e.message ?: "")
            Result.failure(e)
        }
    }
}
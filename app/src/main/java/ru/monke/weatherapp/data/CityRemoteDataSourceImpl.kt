package ru.monke.weatherapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.monke.weatherapp.domain.City
import ru.monke.weatherapp.domain.CityRemoteDataSource
import javax.inject.Inject

private const val BASE_URL = "https://gist.githubusercontent.com/Stronger197/764f9886a1e8392ddcae2521437d5a3b/raw/65164ea1af958c75c81a7f0221bead610590448e/"

class CityRemoteDataSourceImpl @Inject constructor(): CityRemoteDataSource {

    private var api: CityApi

    init {
        api = getApi(baseUrl = BASE_URL)
    }

    override suspend fun getCitiesList(): Result<List<City>> {
        return try {
            val list = api.getCitiesList()
            Result.success(list.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getApi(baseUrl: String): CityApi {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }

}
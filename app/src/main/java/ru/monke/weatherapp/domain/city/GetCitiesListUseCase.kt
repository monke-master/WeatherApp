package ru.monke.weatherapp.domain.city

import javax.inject.Inject

class GetCitiesListUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend fun execute(): Result<List<City>> {
        val res = cityRepository.getCitiesList()
        res.getOrNull()?.let {  cities ->
            val list = cities
                .filter { it.name.isNotEmpty() }
                .sortedBy { it.name }
            return Result.success(list)
        }
        return res
    }

}
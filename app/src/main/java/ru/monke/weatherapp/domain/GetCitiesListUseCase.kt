package ru.monke.weatherapp.domain

import javax.inject.Inject

class GetCitiesListUseCase @Inject constructor(
    private val cityRepository: CityRepository
) {

    suspend fun execute() = cityRepository.getCitiesList()

}
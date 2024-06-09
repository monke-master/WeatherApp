package ru.monke.weatherapp.ui.cities

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.monke.weatherapp.domain.city.GetCitiesListUseCase
import javax.inject.Inject

private const val TAG = "CitiesListViewModel"

@HiltViewModel
class CitiesListViewModel @Inject constructor(
    private val getCitiesListUseCase: GetCitiesListUseCase
): ViewModel() {

    var state = mutableStateOf(CitiesState())
        private set

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true, errorMessage = null)
            val res = getCitiesListUseCase.execute()
            if (res.isFailure) {
                state.value = state.value.copy(
                    isLoading = false,
                    errorMessage = res.exceptionOrNull()?.message)
                return@launch
            }

            val citiesList = res.getOrNull() ?: emptyList()
            state.value = state.value.copy(citiesList = citiesList, isLoading = false)
        }
    }
}
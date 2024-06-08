package ru.monke.weatherapp.ui.weather

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.weather.GetWeatherByCityUseCase
import javax.inject.Inject

private const val TAG = "WeatherViewModel"

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByCityUseCase: GetWeatherByCityUseCase
):ViewModel() {

    var state = mutableStateOf(WeatherState())
        private set

    fun fetchData(city: City) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            val result = getWeatherByCityUseCase.execute(city)
            if (result.isFailure) {
                state.value = state.value.copy(
                    isLoading = false,
                    errorMessage = result.exceptionOrNull()?.message)
                Log.d(TAG, "Error:" + result.exceptionOrNull()?.message.toString())
                return@launch
            }
            Log.d(TAG, "fetchData: " + result.getOrNull())
            state.value = state.value.copy(isLoading = false, weather = result.getOrNull())
        }
    }

}
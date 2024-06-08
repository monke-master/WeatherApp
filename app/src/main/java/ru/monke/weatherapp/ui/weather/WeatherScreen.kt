package ru.monke.weatherapp.ui.weather

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.monke.weatherapp.domain.City

@Composable
fun WeatherScreen(
    city: City?
) {
    Surface {
        Text(text = city?.name ?: "")
    }
}
package ru.monke.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.monke.weatherapp.domain.mockedCities
import ru.monke.weatherapp.ui.cities.CitiesListScreen
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                CitiesListScreen(citiesList = mockedCities)
            }
        }
    }
}

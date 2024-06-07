package ru.monke.weatherapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.monke.weatherapp.ui.cities.CitiesListScreen
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

@AndroidEntryPoint
class WeatherAppMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                CitiesListScreen()
            }
        }
    }
}

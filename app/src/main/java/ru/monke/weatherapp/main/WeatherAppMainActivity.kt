package ru.monke.weatherapp.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.monke.weatherapp.ui.navigation.AppNavigation
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

@AndroidEntryPoint
class WeatherAppMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherAppTheme {
                AppNavigation()
            }
        }
    }
}

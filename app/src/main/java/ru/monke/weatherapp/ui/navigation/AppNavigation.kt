package ru.monke.weatherapp.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import ru.monke.weatherapp.data.CityParcelable
import ru.monke.weatherapp.ui.cities.CitiesListScreen
import ru.monke.weatherapp.ui.weather.WeatherScreen

const val CITIES_ROUTE = "cities_screen"
const val WEATHER_ROUTE = "weather_screen"
const val CITY_ARG = "city"

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CITIES_ROUTE) {
        composable(CITIES_ROUTE) { CitiesListScreen { city ->
            val encodedArg = Uri.encode(Gson().toJson(CityParcelable(city)))
            navController.navigate("$WEATHER_ROUTE?$CITY_ARG=$encodedArg")
        } }
        composable(
            route = "$WEATHER_ROUTE?$CITY_ARG={$CITY_ARG}",
            arguments = listOf(navArgument(CITY_ARG) { type = CityNavType(false) })
        ) { navBackStackEntry ->
            val city = navBackStackEntry.arguments?.getParcelable<CityParcelable>(CITY_ARG)?.city
            WeatherScreen(city)
        }
    }
}
package ru.monke.weatherapp.ui.weather

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.math.MathUtils
import androidx.hilt.navigation.compose.hiltViewModel
import ru.monke.weatherapp.R
import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.mockedCities
import ru.monke.weatherapp.domain.weather.Weather
import ru.monke.weatherapp.ui.common.ErrorText
import ru.monke.weatherapp.ui.common.LoadingIndicator
import ru.monke.weatherapp.ui.theme.WeatherAppTheme
import kotlin.math.floor

@Composable
fun WeatherScreen(
    city: City,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = city) {
        viewModel.fetchData(city)
    }

    val state = viewModel.state
    val weather = state.value.weather
    Surface {
        if (state.value.isLoading) {
            LoadingIndicator()
        } else if (weather != null) {
            WeatherInfo(
                weather = weather,
                city = city)
        } else  {
            ErrorText {
                viewModel.fetchData(city)
            }
        }
    }
}

@Composable
private fun WeatherInfo(
    weather: Weather,
    city: City
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(top = 40.dp),
            text = "${floor(weather.temp).toInt()}°С",
            overflow = TextOverflow.Ellipsis,
            fontSize = 57.sp)
        Text(
            text = city.name,
            overflow = TextOverflow.Ellipsis,
            fontSize = 32.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            modifier = Modifier.padding(bottom = 36.dp),
            onClick = { /*TODO*/ }
        ) {
            Text(text = stringResource(id = R.string.update))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun WeatherInfoPreview() {
    WeatherAppTheme {
        WeatherInfo(Weather(27f), mockedCities[0])
    }
}
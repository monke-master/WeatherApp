package ru.monke.weatherapp.ui.cities

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.monke.weatherapp.domain.city.City
import ru.monke.weatherapp.domain.mockedCities
import ru.monke.weatherapp.ui.common.ErrorText
import ru.monke.weatherapp.ui.common.LoadingIndicator
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

private const val TAG = "CitiesListScreen"

@Composable
fun CitiesListScreen(
    viewModel: CitiesListViewModel = hiltViewModel(),
    onCityItemClicked: (City) -> Unit
) {
    val state = viewModel.state
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        if (state.value.isLoading) {
            LoadingIndicator()
        } else if (!state.value.errorMessage.isNullOrEmpty()){
            ErrorText(onUpdateBtnClicked = viewModel::fetchData)
        } else {
            CitiesList(state.value.citiesList, onCityItemClicked)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListPreview() {
    WeatherAppTheme {
        CitiesList(citiesList = mockedCities.sortedBy { it.name }, {})
    }
}

@Composable
private fun CitiesList(
    citiesList: List<City>,
    onCityItemClicked: (City) -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        val groupedNames = rememberSaveable(citiesList) {
            citiesList.groupBy { it.name[0].toString() }
        }
        val startIndexes = rememberSaveable(citiesList) {
            getStartIndexes(groupedNames.entries)
        }
        val endIndexes = rememberSaveable(citiesList) {
            getEndIndexes(groupedNames.entries)
        }
        val commonModifier = Modifier
            .size(40.dp)
            .padding(start = 8.dp)
        val listState = rememberLazyListState()
        val moveStickyHeader by remember {
            derivedStateOf {
                endIndexes.contains(listState.firstVisibleItemIndex + 1)
            }
        }
        val firstVisibleItemIndex by remember {
            derivedStateOf { listState.firstVisibleItemIndex }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            itemsIndexed(citiesList) { index, city ->
                CityItem(
                    city,
                    showCharHeader = startIndexes.contains(index) && firstVisibleItemIndex != index,
                    commonModifier,
                    onCityItemClicked
                )
            }
        }
        LetterHeader(
            char = citiesList[firstVisibleItemIndex].name.first().toString(),
            modifier = commonModifier.then(
                if (moveStickyHeader) {
                    Modifier.offset {
                        IntOffset(0, -listState.firstVisibleItemScrollOffset + ((16).dp).toPx().toInt())
                    }
                } else {
                    Modifier.offset {
                        IntOffset(0, ((16).dp).toPx().toInt())
                    }
                }
            )
        )
    }
}

@Composable
private fun CityItem(
    city: City,
    showCharHeader: Boolean,
    modifier: Modifier,
    onCityItemClicked: (City) -> Unit
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
            )
            .height(56.dp)
            .fillMaxWidth()
            .clickable { onCityItemClicked(city) },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showCharHeader) {
            LetterHeader(
                char = city.name.first().toString(),
                modifier = modifier
            )
        } else {
            Spacer(modifier = modifier)
        }
        CityNameText(name = city.name)
    }
}

@Composable
private fun CityNameText(
    name: String
) {
    Text(
        text = name,
        modifier = Modifier
            .padding(
                start = 24.dp,
                end = 16.dp)
            .fillMaxWidth(),
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 1
    )
}

@Composable
private fun LetterHeader(char: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = char,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    end = 8.dp,
                ),
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1
        )
    }
}

private fun getStartIndexes(entries: Set<Map.Entry<String, List<City>>>): List<Int> {
    var acc = 0
    val list = mutableListOf<Int>()
    entries.forEach { entry ->
        list.add(acc)
        acc += entry.value.size
    }
    return list
}

private fun getEndIndexes(entries: Set<Map.Entry<String, List<City>>>): List<Int> {
    var acc = 0
    val list = mutableListOf<Int>()
    entries.forEach { entry ->
        acc += entry.value.size
        list.add(acc)
    }
    return list
}


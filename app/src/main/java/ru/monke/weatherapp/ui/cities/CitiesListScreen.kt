package ru.monke.weatherapp.ui.cities

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.monke.weatherapp.domain.City
import ru.monke.weatherapp.domain.mockedCities
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun CitiesListScreen(
    citiesList: List<City>
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        CitiesList(citiesList)
    }
}



@Preview(showBackground = true)
@Composable
private fun ScreenPreview() {
    WeatherAppTheme {
        CitiesListScreen(mockedCities)
    }
}


@Composable
fun CitiesList(
    citiesList: List<City>
) {
    Box(Modifier.fillMaxSize()) {
        val groupedNames = remember(citiesList) {
            citiesList.groupBy { it.name.first().toString() }
        }
        val startIndexes = remember(citiesList) {
            getStartIndexes(groupedNames.entries)
        }
        val endIndexes = remember(citiesList) {
            getEndIndexes(groupedNames.entries)
        }
        val commonModifier = Modifier.width(50.dp)
        val listState = rememberLazyListState()
        val moveStickyHeader by remember {
            derivedStateOf {
                endIndexes.contains(listState.firstVisibleItemIndex + 1)
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState,
        ) {
            itemsIndexed(citiesList) { index, city ->
                NameItem(
                    city.name,
                    showCharHeader = startIndexes.contains(index) && listState.firstVisibleItemIndex != index,
                    commonModifier
                )
            }
        }
        LetterHeader(
            char = citiesList[listState.firstVisibleItemIndex].name.first().toString(),
            modifier = commonModifier.then(
                if (moveStickyHeader) {
                    Modifier.offset {
                        IntOffset(0, -listState.firstVisibleItemScrollOffset)
                    }
                } else {
                    Modifier.offset {
                        IntOffset(0, ((14).dp).toPx().toInt())
                    }
                }
            )
        )
    }
}

@Composable
fun NameItem(
    name: String,
    showCharHeader: Boolean,
    modifier: Modifier
) {
    Row(
        modifier = Modifier
            .padding(
                vertical = 8.dp,
            )
            .height(56.dp)
            .fillMaxWidth()
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (showCharHeader) {
            LetterHeader(
                char = name.first().toString(),
                modifier = modifier
            )
        } else {
            Spacer(modifier = modifier)
        }
        Text(
            text = name,
            modifier = Modifier
                .padding(
                    horizontal = 16.dp
                ),
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun LetterHeader(char: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.size(40.dp),
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


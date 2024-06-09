package ru.monke.weatherapp.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.monke.weatherapp.R
import ru.monke.weatherapp.ui.theme.ButtonTextStyle
import ru.monke.weatherapp.ui.theme.WeatherAppTheme

@Composable
fun ErrorText(
    modifier: Modifier = Modifier,
    onUpdateBtnClicked: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.error_text),
            style = ButtonTextStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        UpdateButton(Modifier.padding(top = 42.dp), onUpdateBtnClicked)
    }
}

@Composable
fun LoadingIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(48.dp)
        )
    }
}

@Composable
fun UpdateButton(
    modifier: Modifier = Modifier,
    onUpdateBtnClicked: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onUpdateBtnClicked) {
        Text(
            text = stringResource(id = R.string.update),
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingPreview() {
    WeatherAppTheme {
        LoadingIndicator()
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorPreview() {
    WeatherAppTheme {
        ErrorText() {}
    }
}

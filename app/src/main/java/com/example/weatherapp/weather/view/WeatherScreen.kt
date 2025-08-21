package com.example.weatherapp.weather.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.models.weatherCityPreview
import com.example.weatherapp.ui.components.CustomCoilImage
import com.example.weatherapp.ui.components.CustomProgressFullScreen
import com.example.weatherapp.ui.components.CustomSnackbar
import com.example.weatherapp.ui.components.CustomTextTitle
import com.example.weatherapp.ui.theme.CommonPaddingDefault
import com.example.weatherapp.ui.theme.CommonPaddingLarge
import com.example.weatherapp.ui.theme.CommonPaddingMin
import com.example.weatherapp.ui.theme.CommonPaddingXLarge
import com.example.weatherapp.ui.theme.MessageVerticalSpace
import com.example.weatherapp.ui.theme.Typography
import com.example.weatherapp.ui.theme.WeatherAppTheme
import com.example.weatherapp.weather.viewmodel.WeatherViewModel
import org.koin.androidx.compose.koinViewModel

//@Preview(showBackground = true)
@Composable
fun WeatherPreview() {
    WeatherAppTheme {
        WeatherView(modifier = Modifier.padding(top = CommonPaddingLarge))
    }
}

@Composable
fun WeatherView(
    modifier: Modifier,
    viewModel: WeatherViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CommonPaddingDefault)
        ) {
            CustomTextTitle(R.string.weather_title)
            WeatherInfoView(uiState.data)
            CustomSnackbar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MessageVerticalSpace)
                ,
                messageRes = uiState.messageRes,
                onDismiss = { viewModel.clearMessage() }
            )
            SearchView { city ->
                viewModel.searchWeather(city)
            }
        }

        CustomProgressFullScreen(visible = uiState.inProgress)
    }
}

@Composable
private fun WeatherInfoView(weatherCity: WeatherCity) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${weatherCity.temp_c.toInt()}°",
            style = Typography.displayLarge
        )
        Text(
            text = weatherCity.name,
            style = Typography.headlineLarge
        )
        Text(
            text = weatherCity.country,
            style = Typography.bodyLarge
        )
        CustomCoilImage(
            url = weatherCity.iconHttps,
            modifier = Modifier
                .size(CommonPaddingXLarge)
                .padding(top = CommonPaddingMin),
            shape = RectangleShape
        )
        Text(
            text = weatherCity.description,
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        Text(
            text = "${weatherCity.wind_kph} km/h",
            style = Typography.bodyLarge
        )
    }
}

@Composable
private fun SearchView(onSearch: (String) -> Unit) {
    var cityValue by remember { mutableStateOf("") }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(CommonPaddingMin)
    ) {
        OutlinedTextField(
            value = cityValue,
            onValueChange = { cityValue = it },
            label = { Text(stringResource(R.string.cities_hint_search_city)) }
        )
        FilledIconButton(onClick = {
            onSearch(cityValue)
        }) {
            Icon(Icons.Rounded.Search, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoPreview() {
    WeatherAppTheme {
        WeatherInfoView(weatherCityPreview)
    }
}
@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    WeatherAppTheme {
        SearchView {}
    }
}
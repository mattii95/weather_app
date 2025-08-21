package com.example.weatherapp.weather.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherapp.R
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.models.weatherCityPreview
import com.example.weatherapp.ui.components.CustomCoilImage
import com.example.weatherapp.ui.components.CustomTextTitle
import com.example.weatherapp.ui.theme.CommonPaddingDefault
import com.example.weatherapp.ui.theme.CommonPaddingLarge
import com.example.weatherapp.ui.theme.CommonPaddingMin
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
        }
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
                .size(CommonPaddingLarge)
                .padding(top = CommonPaddingMin)
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

@Preview(showBackground = true)
@Composable
fun WeatherInfoPreview() {
    WeatherAppTheme {
        WeatherInfoView(weatherCityPreview)
    }
}
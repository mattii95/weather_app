package com.example.weatherapp.cities.view

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R

@Composable
fun CitiesPreview() {
    CitiesView(modifier = Modifier)
}

@Composable
fun CitiesView(modifier: Modifier) {
    Text(stringResource(R.string.weather_title))
}
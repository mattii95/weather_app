package com.example.weatherapp.weather.viewmodel

import com.example.weatherapp.R
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.WeatherCity

data class WeatherUIState(
    val data: WeatherCity = WeatherCity(),
    val items: List<City> = emptyList(),
    val inProgress: Boolean = false,
    val messageRes: Int = R.string.empty_string
)

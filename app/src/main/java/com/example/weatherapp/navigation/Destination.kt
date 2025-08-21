package com.example.weatherapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cloud
import androidx.compose.material.icons.rounded.LocationCity
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.weatherapp.R
import com.example.weatherapp.common.utils.Constants

enum class Destination(
    val route: String,
    val labelRes: Int,
    val icon: ImageVector
) {
    WEATHER(Constants.NAV_WEATHER, R.string.weather_title, Icons.Rounded.Cloud),
    CITIES(Constants.NAV_CITIES, R.string.cities_title, Icons.Rounded.LocationCity)
}
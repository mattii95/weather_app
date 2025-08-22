package com.example.weatherapp.common.entities

data class WeatherResponse(
    val location: City = City(),
    val current: Current = Current()
)

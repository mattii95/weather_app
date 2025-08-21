package com.example.weatherapp.common.entities

data class Condition(
    val text: String = "",
    val icon: String = ""
) {
    val iconHttps get() = "https:$icon"
}


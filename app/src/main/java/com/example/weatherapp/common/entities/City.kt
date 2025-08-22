package com.example.weatherapp.common.entities

data class City(
    val name: String = "",
    val region: String = "",
    val country: String = "",
    val lat: Double = 0.0,
    val lon: Double = 0.0,
    val tz_id: String = ""
) {
    override fun toString(): String {
        return "$name, $country"
    }
}
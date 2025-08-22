package com.example.weatherapp.common.utils

import android.util.Log
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.Weather
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.entities.WeatherResponse

class FormatUtils {
    fun responseToWeatherCity(response: WeatherResponse): WeatherCity? {
        try {
            if (response.location.lat > 90 || response.location.lat < -90 ||
                response.location.lon > 180 || response.location.lon < -180) {
                throw Exception()
            }
            val weatherCity = WeatherCity(
                temp_c = response.current.temp_c,
                description = response.current.condition.text,
                wind_kph = response.current.wind_kph,
                iconHttps = response.current.condition.iconHttps,
                name = response.location.name,
                country = response.location.country,
                lat = response.location.lat,
                lon = response.location.lon
            )
            return weatherCity
        } catch (e: Exception) {
            Log.e("FormatUtils", "responseToWeatherCity: ${e.message}", )
            return null
        }
    }

    fun weatherCityToCity(weatherCity: WeatherCity): City {
        return City(
            name = weatherCity.name,
            country = weatherCity.country,
            lat = weatherCity.lat,
            lon = weatherCity.lon
        )
    }

    fun weatherCityToWeather(weatherCity: WeatherCity): Weather {
        return Weather(
            temp_c = weatherCity.temp_c,
            iconHttps = weatherCity.iconHttps,
            description = weatherCity.description,
            wind_kph = weatherCity.wind_kph
        )
    }
}
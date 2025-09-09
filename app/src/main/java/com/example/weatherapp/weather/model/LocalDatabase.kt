package com.example.weatherapp.weather.model

import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.models.CityDao
import com.example.weatherapp.common.models.WeatherCityDao
import com.example.weatherapp.common.models.WeatherDao
import com.example.weatherapp.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalDatabase(
    private val cityDao: CityDao,
    private val weatherDao: WeatherDao,
    private val weatherCityDao: WeatherCityDao,
    private val utils: FormatUtils
) {

    suspend fun addWeatherAndCity(weatherCity: WeatherCity, onResult: (Boolean) -> Unit) =
        withContext(Dispatchers.IO) {
            val city = utils.weatherCityToCity(weatherCity)
            val weather = utils.weatherCityToWeather(weatherCity)
            val result = weatherCityDao.addCityAndWeather(city, weather)
            onResult(result > 0)
        }

    suspend fun getAllCities(onResult: (List<City>) -> Unit) = withContext(Dispatchers.IO) {
        onResult(cityDao.getAllCities())
    }

    suspend fun getWeatherCityByCityId(cityId: Long, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO) {
            onResult(weatherDao.getWeatherCityByCityId(cityId))
        }
}
package com.example.weatherapp.common.models

import androidx.room.Dao
import androidx.room.Transaction
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.Weather

@Dao
interface WeatherCityDao: CityDao, WeatherDao {
    @Transaction
    suspend fun addCityAndWeather(city: City, weather: Weather): Long {
        val dbCity = getCityByNameAndCountry(city.name, city.country)
        if (dbCity == null) {
            val cityId = addCity(city)
            return addWeather(weather.copy(cityId = cityId))
        } else {
            getWeatherByCityId(dbCity.id)?.let { dbWeather ->
                return updateWeather(
                    weather.copy(
                        id = dbWeather.id,
                        cityId = dbWeather.cityId
                    )
                ).toLong()
            }
        }
        return 0
    }
}
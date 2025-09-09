package com.example.weatherapp.common.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.common.entities.Weather
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.utils.Constants

@Dao
interface WeatherDao {
    @Insert
    suspend fun addWeather(weather: Weather): Long

    @Update
    suspend fun updateWeather(weather: Weather): Int

    @Delete
    suspend fun deleteWeather(weather: Weather): Int

    @Query("SELECT * FROM ${Constants.ENTITY_WEATHER} " +
            "WHERE ${Constants.P_CITY_ID} = :cityId " +
            "LIMIT 1")
    suspend fun getWeatherByCityId(cityId: Long): Weather?

    @Query("SELECT * FROM ${Constants.ENTITY_WEATHER} " +
            "INNER JOIN ${Constants.ENTITY_CITY} " +
            "ON ${Constants.ENTITY_WEATHER}.cityId = ${Constants.ENTITY_CITY}.id " +
            "WHERE ${Constants.ENTITY_WEATHER}.cityId = :cityId " +
            "LIMIT 1")
    suspend fun getWeatherCityByCityId(cityId: Long): WeatherCity?
}
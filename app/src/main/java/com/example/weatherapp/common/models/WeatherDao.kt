package com.example.weatherapp.common.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import com.example.weatherapp.common.entities.Weather

@Dao
interface WeatherDao {
    @Insert
    suspend fun addWeather(weather: Weather): Long

    @Update
    suspend fun updateWeather(weather: Weather): Int

    @Delete
    suspend fun deleteWeather(weather: Weather): Int
}
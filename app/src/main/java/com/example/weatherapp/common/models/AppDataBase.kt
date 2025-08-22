package com.example.weatherapp.common.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.Weather
import com.example.weatherapp.common.utils.Constants

@Database(entities = [City::class, Weather::class], version = Constants.DATABASE_INIT_VERSION)
abstract class AppDataBase: RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun weatherDao(): WeatherDao
}
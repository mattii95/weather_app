package com.example.weatherapp.common.di

import android.app.Application
import androidx.room.Room
import com.example.weatherapp.common.models.AppDataBase
import com.example.weatherapp.common.models.CityDao
import com.example.weatherapp.common.models.WeatherDao
import com.example.weatherapp.common.utils.Constants
import org.koin.dsl.module

fun provideDatabase(application: Application): AppDataBase {
    return Room.databaseBuilder(
        context = application,
        name = Constants.DATABASE_NAME,
        klass = AppDataBase::class.java
    ).build()
}

fun provideCityDao(dataBase: AppDataBase): CityDao = dataBase.cityDao()

fun provideWeatherDao(dataBase: AppDataBase): WeatherDao = dataBase.weatherDao()

val localDatasourceModule = module {
    single { provideCityDao(get()) }
    single { provideWeatherDao(get()) }
    single { provideDatabase(get()) }
}
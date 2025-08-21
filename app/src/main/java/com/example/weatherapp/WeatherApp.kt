package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.common.di.commonModule
import com.example.weatherapp.weather.di.remoteDataSourceModule
import com.example.weatherapp.weather.di.weatherModule
import org.koin.core.context.startKoin

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(weatherModule, commonModule, remoteDataSourceModule)
        }
    }
}
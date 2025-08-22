package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.common.di.commonModule
import com.example.weatherapp.common.di.localDatasourceModule
import com.example.weatherapp.weather.di.remoteDataSourceModule
import com.example.weatherapp.weather.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WeatherApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WeatherApp)
            modules(weatherModule, commonModule, remoteDataSourceModule, localDatasourceModule)
        }
    }
}
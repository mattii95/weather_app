package com.example.weatherapp.weather.di

import com.example.weatherapp.weather.model.RemoteDatabase
import com.example.weatherapp.weather.viewmodel.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    single { RemoteDatabase(null, get()) }
    viewModel { WeatherViewModel(get()) }
}
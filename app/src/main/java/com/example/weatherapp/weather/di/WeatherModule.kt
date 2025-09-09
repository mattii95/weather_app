package com.example.weatherapp.weather.di

import com.example.weatherapp.weather.model.LocalDatabase
import com.example.weatherapp.weather.model.RemoteDatabase
import com.example.weatherapp.weather.viewmodel.WeatherViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val weatherModule = module {
    single { RemoteDatabase(get(), get()) }
    single { LocalDatabase(get(), get(), get(), get()) }
    viewModel { WeatherViewModel(get(), get()) }
}
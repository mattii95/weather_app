package com.example.weatherapp.weather.di

import com.example.weatherapp.common.utils.Constants
import com.example.weatherapp.weather.model.WeatherService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(gsonConverterFactory)
        .build()
}

fun provideWeatherService(retrofit: Retrofit) : WeatherService {
    return retrofit.create(WeatherService::class.java)
}

val remoteDataSourceModule = module {
    single { provideGsonConverterFactory() }
    single { provideRetrofit(get()) }
    single { provideWeatherService(get()) }
}
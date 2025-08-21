package com.example.weatherapp.common.di

import com.example.weatherapp.common.utils.FormatUtils
import org.koin.dsl.module

val commonModule = module {
    single { FormatUtils() }
}
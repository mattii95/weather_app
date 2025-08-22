package com.example.weatherapp.common.utils

object Constants {
    // Navigation
    const val NAV_WEATHER = "nav_weather"
    const val NAV_CITIES = "nav_cities"

    // Retrofit
    const val BASE_URL = "https://api.weatherapi.com"
    const val PATH_V1 = "/v1/current.json"
    const val API_KEY = "9b8550792d884da780e152359251908"
    const val LANG_ES = "es"
    const val PARAM_KEY = "key"
    const val PARAM_QUERY = "q"
    const val PARAM_LANGUAGE = "lang"

    // Room
    const val DATABASE_NAME = "db_weather"
    const val DATABASE_INIT_VERSION = 1
    // ENTITIES
    const val ENTITY_CITY = "cities"
    const val ENTITY_WEATHER = "weathers"
    // PROPERTIES
    const val P_NAME = "name"
    const val P_COUNTRY = "country"
    const val P_CITY_ID = "cityId"

    const val DURATION_SHORT = 3_000L
    const val DURATION_LONG = 8_000L
}
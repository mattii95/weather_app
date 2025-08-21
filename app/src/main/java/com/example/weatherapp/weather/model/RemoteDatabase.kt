package com.example.weatherapp.weather.model

import android.util.Log
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.utils.Constants
import com.example.weatherapp.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RemoteDatabase(
    private val service: WeatherService,
    private val utils: FormatUtils
) {

    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO){
            try {
                val result = service.searchWeatherByName(
                    key = Constants.API_KEY,
                    name = name,
                    lang = Constants.LANG_ES
                )
                onResult(utils.responseToWeatherCity(result))
            } catch (e: Exception) {
                Log.e("RemoteDatabase", "searchWeatherByName: ${e.message}")
                onResult(null)
            }
        }
}
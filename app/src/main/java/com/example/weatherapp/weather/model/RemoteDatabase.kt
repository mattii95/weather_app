package com.example.weatherapp.weather.model

import android.util.Log
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.common.utils.Constants
import com.example.weatherapp.common.utils.FormatUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDatabase(
    private val service: WeatherService? = null,
    private val utils: FormatUtils
) {
    private fun setupRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun weatherService() : WeatherService {
        return setupRetrofit().create(WeatherService::class.java)
    }

    suspend fun searchWeatherByName(name: String, onResult: (WeatherCity?) -> Unit) =
        withContext(Dispatchers.IO){
            try {
                val result = weatherService().searchWeatherByName(
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
package com.example.weatherapp.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.entities.WeatherCity
import com.example.weatherapp.weather.model.LocalDatabase
import com.example.weatherapp.weather.model.RemoteDatabase
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val rdb: RemoteDatabase,
    private val ldb: LocalDatabase
): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUIState())
    val uiState: StateFlow<WeatherUIState> = _uiState.asStateFlow()

    init {
        searchWeather("Argentina")
        getAllCities()
    }

    fun searchWeather(name: String) {
        executeAction {
            rdb.searchWeatherByName(name) { result ->
                if (result != null) {
                    _uiState.update { it.copy(data = result) }
                } else {
                    _uiState.update { it.copy(messageRes = R.string.weather_search_error) }
                }
            }
        }
    }

    fun saveWeatherCity(weatherCity: WeatherCity) {
        executeAction {
            ldb.addWeatherAndCity(weatherCity) { success ->
                if (success) {
                    _uiState.update { it.copy(messageRes = R.string.weather_local_save_success) }
                } else {
                    _uiState.update { it.copy(messageRes = R.string.weather_local_save_error) }
                }
            }
        }
    }

    fun getWeatherBiCity(city: City) {
        executeAction {
            ldb.getWeatherCityByCityId(city.id) { result ->
                if (result != null) {
                    _uiState.update { it.copy(data = result) }
                } else {
                    _uiState.update { it.copy(messageRes = R.string.weather_local_by_city_error) }
                }
            }
        }
    }

    fun clearMessage() {
        _uiState.update { it.copy(messageRes = R.string.empty_string) }
    }

    private fun getAllCities() {
        executeAction {
            ldb.getAllCities { result ->
                if (result.isNotEmpty()) {
                    _uiState.update { it.copy(items = result) }
                } else {
                    _uiState.update { it.copy(messageRes = R.string.weather_empty_list) }
                }
            }
        }
    }

    private fun executeAction(block: suspend () -> Unit): Job {
        return viewModelScope.launch {
            _uiState.update { it.copy(inProgress = true) }
            try {
                block()
            } catch (e: Exception) {
                _uiState.update { it.copy(messageRes = R.string.general_error) }
            } finally {
                _uiState.update { it.copy(inProgress = false) }
            }
        }
    }

}
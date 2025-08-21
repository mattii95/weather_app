package com.example.weatherapp.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.R
import com.example.weatherapp.weather.model.RemoteDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WeatherViewModel(private val rdb: RemoteDatabase): ViewModel() {
    private val _uiState = MutableStateFlow(WeatherUIState())
    val uiState: StateFlow<WeatherUIState> = _uiState.asStateFlow()

    init {
        searchWeather("Argentina")
    }

    fun searchWeather(name: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(inProgress = true) }
            try {
                rdb.searchWeatherByName(name) { result ->
                    if (result != null) {
                        _uiState.update { it.copy(data = result) }
                    } else {
                        _uiState.update { it.copy(messageRes = R.string.weather_search_error) }
                    }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(messageRes = R.string.general_error) }
            } finally {
                _uiState.update { it.copy(inProgress = false) }
            }
        }
    }
}
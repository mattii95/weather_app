package com.example.weatherapp.common.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.weatherapp.common.entities.City
import com.example.weatherapp.common.utils.Constants

@Dao
interface CityDao {

    @Insert
    suspend fun addCity(city: City): Long

    @Update
    suspend fun updateCity(city: City): Int

    @Delete
    suspend fun deleteCity(city: City): Int

    @Query("SELECT * FROM ${Constants.ENTITY_CITY}")
    suspend fun getAllCities(): List<City>

    @Query("SELECT * FROM ${Constants.ENTITY_CITY} " +
            "WHERE ${Constants.P_NAME} = :name " +
            "AND ${Constants.P_COUNTRY} = :country " +
            "Limit 1")
    suspend fun getCityByNameAndCountry(name: String, country: String): City?
}
package com.example.weatherapp.common.entities

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.example.weatherapp.common.utils.Constants

@Entity(tableName = Constants.ENTITY_CITY)
data class City(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var name: String = "",
    var region: String = "",
    var country: String = "",
    var lat: Double = 0.0,
    var lon: Double = 0.0,
    @Ignore var tz_id: String = ""
) {
    override fun toString(): String {
        return "$name, $country"
    }
}
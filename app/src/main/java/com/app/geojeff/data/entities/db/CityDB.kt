package com.app.geojeff.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.geojeff.data.entities.City

@Entity(tableName = "search_city")
data class CityDB(
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "country_name") val countryName: String? = null,
    @ColumnInfo(name = "country_code") val countryCode: String? = null,
    @ColumnInfo(name = "continent_code") val continentCode: String? = null,
    @ColumnInfo(name = "lat") val lat: String? = null,
    @ColumnInfo(name = "lng") val lng: String? = null,
    @ColumnInfo(name = "population") val population: Int? = null,
    @ColumnInfo(name = "cardinal_direction") val cardinalDirection: CardinalDirectionDB? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int? = null
)

fun CityDB.toModel(): City {
    return City(
        this.name,
        this.countryName,
        this.countryCode,
        this.continentCode,
        this.lat,
        this.lng,
        this.population,
        this.cardinalDirection?.toModel()
    )
}
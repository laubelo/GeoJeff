package com.app.geojeff.data.entities

import com.app.geojeff.data.entities.db.CityDB
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class City(
    val name: String?,
    val countryName: String?,
    val countryCode: String?,
    val continentCode: String?,
    val lat: String?,
    val lng: String?,
    val population: Int?,
    @SerializedName("bbox")
    val cardinalDirection: CardinalDirection?
) : Serializable

fun City.toDB(): CityDB {
    return CityDB(
        this.name,
        this.countryName,
        this.countryCode,
        this.continentCode,
        this.lat,
        this.lng,
        this.population,
        this.cardinalDirection?.toDB()
    )
}
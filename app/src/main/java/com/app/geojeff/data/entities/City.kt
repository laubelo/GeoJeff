package com.app.geojeff.data.entities

import com.google.gson.annotations.SerializedName

data class City(
    val name: String,
    val countryName: String,
    val continentCode: String,
    val lat: String,
    val lng: String,
    val population: Int,
    @SerializedName("bbox")
    val cardinalDirection: CardinalDirection
)
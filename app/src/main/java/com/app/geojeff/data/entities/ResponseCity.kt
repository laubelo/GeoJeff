package com.app.geojeff.data.entities

import com.google.gson.annotations.SerializedName

data class ResponseCity(
    val totalResultsCount: Int? = 0,
    @SerializedName("geonames")
    val geoNames: List<City>? = null
)
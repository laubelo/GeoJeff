package com.app.geojeff.data.entities

import java.io.Serializable

data class CardinalDirection(
    val east: Double,
    val west: Double,
    val north: Double,
    val south: Double
) : Serializable
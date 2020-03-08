package com.app.geojeff.data.entities

import com.app.geojeff.data.entities.db.CardinalDirectionDB
import java.io.Serializable

data class CardinalDirection(
    val east: Double?,
    val west: Double?,
    val north: Double?,
    val south: Double?
) : Serializable

fun CardinalDirection.toDB(): CardinalDirectionDB {
    return CardinalDirectionDB(
        this.east,
        this.west,
        this.north,
        this.south
    )
}
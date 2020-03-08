package com.app.geojeff.data.entities.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.app.geojeff.data.entities.CardinalDirection

@Entity(tableName = "city_cardinal_direction")
data class CardinalDirectionDB(
    @ColumnInfo(name = "east") val east: Double? = null,
    @ColumnInfo(name = "west") val west: Double? = null,
    @ColumnInfo(name = "north") val north: Double? = null,
    @ColumnInfo(name = "south") val south: Double? = null,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id") val id: Int? = null
)

fun CardinalDirectionDB.toModel(): CardinalDirection {
    return CardinalDirection(
        this.east,
        this.west,
        this.north,
        this.south
    )
}


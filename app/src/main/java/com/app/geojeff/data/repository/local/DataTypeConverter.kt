package com.app.geojeff.data.repository.local

import androidx.room.TypeConverter
import com.app.geojeff.data.entities.db.CardinalDirectionDB
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

class DataTypeConverter {

    companion object {

        var gson = Gson()

        @TypeConverter
        @JvmStatic
        fun fromCardinalDirection(cardinalDirectionDB: CardinalDirectionDB?): String? =
            gson.toJson(cardinalDirectionDB)

        @TypeConverter
        @JvmStatic
        fun toCardinalDirection(cardinalDirection: String?): CardinalDirectionDB? =
            try {
                gson.fromJson(cardinalDirection, CardinalDirectionDB::class.java)
            } catch (e: JsonSyntaxException) {
                null
            }

    }

}
package com.app.geojeff.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.app.geojeff.data.entities.db.CityDB
import com.app.geojeff.data.repository.local.dao.CityDao

@Database(
    entities = [CityDB::class],
    version = 1
)
@TypeConverters(DataTypeConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getCityDao(): CityDao
}
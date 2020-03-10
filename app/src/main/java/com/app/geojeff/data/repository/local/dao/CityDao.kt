package com.app.geojeff.data.repository.local.dao

import androidx.room.*
import com.app.geojeff.data.entities.db.CityDB

@Dao
interface CityDao {

    //insert city
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityDB: CityDB)

    //get search history
    @Transaction
    @Query("Select * from search_city")
    suspend fun getSearchHistory(): List<CityDB>

}
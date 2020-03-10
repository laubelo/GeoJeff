package com.app.geojeff.data.repository.remote

import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.data.entities.ResponseWeather
import com.app.geojeff.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("searchJSON?maxRows=20&startRow=0&lang=en&isNameRequired=true&style=FULL")
    suspend fun getCities(
        @Query("q") cityName: String,
        @Query("username") username: String = Constants.USERNAME
    ): ResponseCity

    @GET("weatherJSON")
    suspend fun getCityWeather(
        @Query("north") north: Double?,
        @Query("south") south: Double?,
        @Query("east") east: Double?,
        @Query("west") west: Double?,
        @Query("username") username: String = Constants.USERNAME
    ): ResponseWeather

}
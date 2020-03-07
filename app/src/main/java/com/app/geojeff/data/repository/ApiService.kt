package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("searchJSON?maxRows=20&startRow=0&lang=en&isNameRequired=true&style=FULL")
    suspend fun getCities(
        @Query("q") cityName: String,
        @Query("username") username: String = Constants.username
    ): ResponseCity

}
package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.City
import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.data.entities.ResponseWeather
import com.app.geojeff.data.repository.remote.DataState

interface ApiRepository {

    suspend fun getCities(cityName: String): DataState<ResponseCity>

    suspend fun getCityWeather(
        north: Double?,
        south: Double?,
        east: Double?,
        west: Double?
    ): DataState<ResponseWeather>

    suspend fun getSearchHistory(): List<City>

    suspend fun addCityToSearchHistory(city: City)

}
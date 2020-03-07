package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.ResponseCity


interface ApiRepository {

    suspend fun getCities(cityName: String): DataState<ResponseCity>

}
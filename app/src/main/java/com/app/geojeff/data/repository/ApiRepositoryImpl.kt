package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.data.entities.ResponseWeather
import java.lang.Exception

class ApiRepositoryImpl(private val remoteClient: RemoteClient) : ApiRepository {

    override suspend fun getCities(cityName: String): DataState<ResponseCity> {
        return try {
            val result = remoteClient.getRemoteClient().getCities(cityName)
            DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    override suspend fun getCityWeather(
        north: Double,
        south: Double,
        east: Double,
        west: Double
    ): DataState<ResponseWeather> {
        return try {
            val result = remoteClient.getRemoteClient().getCityWeather(north, south, east, west)
            DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

}
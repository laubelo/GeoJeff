package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.ResponseCity
import java.lang.Exception

class ApiRepositoryImpl(private val remoteClient: RemoteClient): ApiRepository {

    override suspend fun getCities(cityName: String): DataState<ResponseCity>{
        return try {
            val result = remoteClient.getRemoteClient().getCities(cityName)
            DataState.Success(result)
        } catch (exception: Exception){
            DataState.Error(exception)
        }
    }

}
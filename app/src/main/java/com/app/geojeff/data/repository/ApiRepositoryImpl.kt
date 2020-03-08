package com.app.geojeff.data.repository

import com.app.geojeff.data.entities.City
import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.data.entities.ResponseWeather
import com.app.geojeff.data.entities.db.CityDB
import com.app.geojeff.data.entities.db.toModel
import com.app.geojeff.data.entities.toDB
import com.app.geojeff.data.repository.local.LocalDatabase
import com.app.geojeff.data.repository.remote.DataState
import com.app.geojeff.data.repository.remote.RemoteClient

class ApiRepositoryImpl(
    private val remoteClient: RemoteClient,
    private val localDatabase: LocalDatabase
) : ApiRepository {

    override suspend fun getCities(cityName: String): DataState<ResponseCity> {
        return try {
            val result = remoteClient.getRemoteClient().getCities(cityName)
            DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    override suspend fun getCityWeather(
        north: Double?,
        south: Double?,
        east: Double?,
        west: Double?
    ): DataState<ResponseWeather> {
        return try {
            val result = remoteClient.getRemoteClient().getCityWeather(north, south, east, west)
            DataState.Success(result)
        } catch (exception: Exception) {
            DataState.Error(exception)
        }
    }

    override suspend fun getSearchHistory(): List<City> {
        val listDB: List<CityDB> = localDatabase.getCityDao().getSearchHistory()
        val cityList: ArrayList<City> = ArrayList()

        listDB.forEach { cityDB ->
            cityList.add(cityDB.toModel())
        }

        return cityList
    }

    override suspend fun addCityToSearchHistory(city: City) {
        localDatabase.getCityDao().insert(city.toDB())
    }

}
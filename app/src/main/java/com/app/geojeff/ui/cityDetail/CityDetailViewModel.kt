package com.app.geojeff.ui.cityDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.geojeff.data.entities.Weather
import com.app.geojeff.data.repository.ApiRepository
import com.app.geojeff.data.repository.DataState
import com.app.geojeff.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CityDetailViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    val weather = MutableLiveData<List<Weather>>()

    fun getWeather(
        north: Double,
        south: Double,
        east: Double,
        west: Double
    ) {
        launch {
            val result =
                withContext(Dispatchers.IO) {
                    apiRepository.getCityWeather(
                        north,
                        south,
                        east,
                        west
                    )
                }

            when (result) {
                is DataState.Success -> weather.value = result.data.weatherObservations
                is DataState.Error -> Log.e("error", result.exception.message)
            }
        }
    }

}
package com.app.geojeff.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.app.geojeff.data.entities.City
import com.app.geojeff.data.entities.ResponseCity
import com.app.geojeff.data.repository.ApiRepository
import com.app.geojeff.data.repository.remote.DataState
import com.app.geojeff.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    val responseCity = MutableLiveData<ResponseCity>()
    private var job: Job = Job()

    fun getCities(cityName: String) {
        //avoid to launch multiple calls at typing
        if (job.isActive) {
            job.cancel()
        }

        job = launch {
            val result = withContext(Dispatchers.IO) { apiRepository.getCities(cityName) }
            when (result) {
                is DataState.Success -> responseCity.value = result.data
                is DataState.Error -> Log.e("error", result.exception.message)
            }
        }
    }

    fun addCityToSearchHistory(city: City) {
        //insert city to database
        launch {
            apiRepository.addCityToSearchHistory(city)
        }
    }

}
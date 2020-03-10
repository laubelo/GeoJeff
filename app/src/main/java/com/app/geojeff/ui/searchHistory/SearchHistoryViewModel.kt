package com.app.geojeff.ui.searchHistory

import androidx.lifecycle.MutableLiveData
import com.app.geojeff.data.entities.City
import com.app.geojeff.data.repository.ApiRepository
import com.app.geojeff.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchHistoryViewModel(private val apiRepository: ApiRepository) : BaseViewModel() {

    val searchHistory = MutableLiveData<List<City>>()

    fun getSearchHistoryList() {
        //get search history from database
        launch {
            val result = withContext(Dispatchers.IO) { apiRepository.getSearchHistory() }
            searchHistory.value = result
        }
    }

}
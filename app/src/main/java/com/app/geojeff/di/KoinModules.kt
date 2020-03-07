package com.app.geojeff.di

import com.app.geojeff.data.repository.ApiRepository
import com.app.geojeff.data.repository.ApiRepositoryImpl
import com.app.geojeff.data.repository.RemoteClient
import com.app.geojeff.ui.cityDetail.CityDetailViewModel
import com.app.geojeff.ui.common.Navigator
import com.app.geojeff.ui.home.HomeViewModel
import com.app.geojeff.ui.searchHistory.SearchHistoryViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteClient() }
    single<ApiRepository> { ApiRepositoryImpl(get()) }
    single{Navigator()}

    viewModel { HomeViewModel(get()) }
    viewModel { SearchHistoryViewModel() }
    viewModel { CityDetailViewModel(get()) }
}
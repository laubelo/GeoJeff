package com.app.geojeff.di

import androidx.room.Room
import com.app.geojeff.data.repository.ApiRepository
import com.app.geojeff.data.repository.ApiRepositoryImpl
import com.app.geojeff.data.repository.local.LocalDatabase
import com.app.geojeff.data.repository.remote.RemoteClient
import com.app.geojeff.ui.cityDetail.CityDetailViewModel
import com.app.geojeff.ui.common.Navigator
import com.app.geojeff.ui.home.HomeViewModel
import com.app.geojeff.ui.searchHistory.SearchHistoryViewModel
import com.app.geojeff.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    //singletons
    single { RemoteClient() }
    single<ApiRepository> { ApiRepositoryImpl(get(), get()) }
    single {
        Room.databaseBuilder(androidContext(), LocalDatabase::class.java, Constants.DB_NAME).build()
    }
    single { Navigator() }

    //viewModels
    viewModel { HomeViewModel(get()) }
    viewModel { SearchHistoryViewModel(get()) }
    viewModel { CityDetailViewModel(get()) }
}
package com.app.geojeff.app

import android.app.Application
import com.app.geojeff.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GeoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoinModules()
    }

    private fun initKoinModules() {
        //init dependency injection with Koin
        startKoin {
            androidContext(this@GeoApplication)
            modules(appModule)
        }
    }

}
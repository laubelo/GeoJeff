package com.app.geojeff.ui.cityDetail

import android.util.Log
import androidx.lifecycle.Observer
import com.app.geojeff.R
import com.app.geojeff.data.entities.City
import com.app.geojeff.ui.common.BaseActivity
import com.app.geojeff.utils.Constants
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_city_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class CityDetailActivity : BaseActivity(), OnMapReadyCallback {

    override val viewModel by viewModel<CityDetailViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_city_detail

    override fun getActionBarTitle(): String = getString(R.string.city_detail_title)

    override fun displayBackButton(): Boolean = true

    private var googleMap: GoogleMap? = null

    private lateinit var city: City

    override fun initView() {
        intent?.let {
            city = it.getSerializableExtra(Constants.INTENT_EXTRA_CITY_DETAIL) as City
            setCityDetails()
            initGoogleMaps()
            val cardinalDirection = city.cardinalDirection
            viewModel.getWeather(
                cardinalDirection.north,
                cardinalDirection.south,
                cardinalDirection.east,
                cardinalDirection.west
            )
            setObservers()
        }
    }

    private fun setObservers() {
        viewModel.weather.observe(this, Observer { weather ->
            var temperature = 0
            weather.forEach { item ->
                item.temperature?.let {
                    temperature += it.toInt()
                }
            }
            val totalTemp = temperature / weather.size
            Log.e("temperature", totalTemp.toString())
        })
    }

    private fun setCityDetails() {
        val title = city.name + ", " + city.countryName + " " + city.continentCode
        val population = city.population.toString() + " population"
        text_name.text = title
        text_population.text = population
    }

    private fun initGoogleMaps() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(map: GoogleMap?) {
        googleMap = map

        googleMap?.let {
            val latLng = LatLng(city.lat.toDouble(), city.lng.toDouble())
            val zoomLevel = 12.0f
            val markerOptions: MarkerOptions = MarkerOptions().position(latLng)
            it.addMarker(markerOptions)
            it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
        }
    }

}

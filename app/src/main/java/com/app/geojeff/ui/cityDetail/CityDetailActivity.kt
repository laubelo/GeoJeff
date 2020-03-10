package com.app.geojeff.ui.cityDetail

import android.view.View
import android.widget.SeekBar
import androidx.lifecycle.Observer
import com.app.geojeff.R
import com.app.geojeff.data.entities.City
import com.app.geojeff.ui.common.BaseActivity
import com.app.geojeff.utils.Constants
import com.app.geojeff.utils.SeekBarAnimation
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_city_detail.*
import org.koin.android.viewmodel.ext.android.viewModel


class CityDetailActivity : BaseActivity(), OnMapReadyCallback, SeekBar.OnSeekBarChangeListener {

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
            cardinalDirection?.let { weather ->
                viewModel.getWeather(
                    weather.north,
                    weather.south,
                    weather.east,
                    weather.west
                )
            }

            if (cardinalDirection == null) {
                hideTemperatureBar()
            }

            setObservers()
        }
    }

    private fun setObservers() {
        viewModel.weather.observe(this, Observer { weather ->
            var temperature = 0f
            if (weather.isNotEmpty()) {
                weather.forEach { item ->
                    item.temperature?.let {
                        temperature += it.toFloat()
                    }
                }

                val totalTemp = temperature / weather.size
                setupSeekBarTemperature(totalTemp)
            } else {
                hideTemperatureBar()
            }
        })
    }

    private fun hideTemperatureBar() {
        linear_temperature.visibility = View.GONE
        text_no_temperature.visibility = View.VISIBLE
    }

    private fun setupSeekBarTemperature(totalTemp: Float) {
        val progressBarAnimation = SeekBarAnimation(seek_temperature, 0f, totalTemp)
        progressBarAnimation.duration = 900
        seek_temperature.startAnimation(progressBarAnimation)
        seek_temperature.setOnSeekBarChangeListener(this)
    }

    private fun setCityDetails() {
        val title = city.name + ", " + city.countryName + " " + city.continentCode
        val population = city.population.toString() + " " + getString(R.string.population)
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
            city.lat?.let { lat ->
                city.lng?.let { lng ->
                    val latLng = LatLng(lat.toDouble(), lng.toDouble())
                    val zoomLevel = 12.0f
                    val markerOptions: MarkerOptions = MarkerOptions().position(latLng)
                    it.addMarker(markerOptions)
                    it.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel))
                }
            }
        }
    }


    //seekBar change listener methods
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, p2: Boolean) {
        seekBar?.let {
            //amount 5px to maximum value for center the text in progress value
            val value = (progress * (it.width - 2 * it.thumbOffset)) / (it.max + 5)
            val temperature = progress.toString() + getString(R.string.celsius)
            text_temperature.text = temperature
            text_temperature.x = it.x + value + it.thumbOffset / 2
        }
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {
        //Do nothing
    }

    override fun onStopTrackingTouch(p0: SeekBar?) {
        //Do nothing
    }

}

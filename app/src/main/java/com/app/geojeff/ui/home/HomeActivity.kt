package com.app.geojeff.ui.home

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import com.app.geojeff.R
import com.app.geojeff.data.entities.City
import com.app.geojeff.ui.common.BaseActivity
import com.app.geojeff.ui.home.adapter.CitiesAdapter
import com.app.geojeff.utils.AlertUtils
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : BaseActivity(), CitiesAdapter.OnCityClick {

    override val viewModel by viewModel<HomeViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun getActionBarTitle(): String = getString(R.string.home_title)

    override fun displayBackButton(): Boolean = false

    private lateinit var citiesAdapter: CitiesAdapter

    private var cities: ArrayList<City> = ArrayList()

    override fun initView() {
        AlertUtils.showAlertDialog(this)
        setViewListeners()
        setObservers()
        initAdapter()
    }

    private fun initAdapter() {
        citiesAdapter = CitiesAdapter(cities, this)
        recycler_cities.adapter = citiesAdapter
    }

    private fun setViewListeners() {
        linear_history.setOnClickListener(object : View(this), View.OnClickListener {
            override fun onClick(p0: View?) {
                //show search history
                navigator.goToHistory(this@HomeActivity)
            }
        })

        edit_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let {
                    val text = p0.toString()

                    //show/hide clear text action and placeholder
                    if (text.trim().isNotEmpty()) {
                        image_clear.visibility = View.VISIBLE
                        linear_placeholder.visibility = View.GONE
                    } else {
                        image_clear.visibility = View.GONE
                        linear_placeholder.visibility = View.VISIBLE
                    }

                    //execute the call
                    searchACity(text)
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                //Do nothing
            }

        })

        image_clear.setOnClickListener {
            clearSearchBar()
        }
    }

    private fun searchACity(text: String) {
        cities.clear()
        viewModel.getCities(text)
        citiesAdapter.notifyDataSetChanged()
    }

    private fun clearSearchBar() {
        edit_search.text.clear()
        edit_search.hideKeyboard()
    }

    private fun setObservers() {
        viewModel.responseCity.observe(this, Observer { result ->
            cities.clear()
            result.totalResultsCount?.let { count ->
                if (count == 0 && edit_search.text.trim().isNotEmpty()) {
                    //show no results text
                    text_no_results.visibility = View.VISIBLE
                } else {
                    result.geoNames?.let { list ->
                        text_no_results.visibility = View.GONE
                        cities.addAll(list)
                    }
                }
            }
            citiesAdapter.notifyDataSetChanged()
        })
    }

    //on city click interface method
    override fun onClick(city: City?) {
        city?.let {
            viewModel.addCityToSearchHistory(it)
            navigator.goToDetail(this, it)
        }
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}

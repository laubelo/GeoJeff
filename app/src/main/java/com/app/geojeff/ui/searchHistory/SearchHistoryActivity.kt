package com.app.geojeff.ui.searchHistory

import android.view.View
import androidx.lifecycle.Observer
import com.app.geojeff.R
import com.app.geojeff.data.entities.City
import com.app.geojeff.ui.common.BaseActivity
import com.app.geojeff.ui.home.adapter.CitiesAdapter
import kotlinx.android.synthetic.main.activity_search_history.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchHistoryActivity : BaseActivity(), CitiesAdapter.OnCityClick {

    override val viewModel by viewModel<SearchHistoryViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_search_history

    override fun getActionBarTitle(): String = getString(R.string.history_title)

    override fun displayBackButton(): Boolean = true

    override fun initView() {
        viewModel.getSearchHistoryList()
        setObservers()
    }

    private fun setObservers() {
        viewModel.searchHistory.observe(this, Observer { cities ->
            cities?.let {
                if (it.isNotEmpty()) {
                    setAdapter(it)
                } else {
                    showPlaceholderText()
                }
            }
        })
    }

    private fun showPlaceholderText() {
        recycler_historic.visibility = View.GONE
        text_no_historic.visibility = View.VISIBLE
    }

    private fun setAdapter(cities: List<City>) {
        val adapter = CitiesAdapter(cities, this)
        recycler_historic.adapter = adapter
    }

    //on city click interface method
    override fun onClick(city: City?) {
        city?.let {
            navigator.goToDetail(this, it)
        }
    }

}

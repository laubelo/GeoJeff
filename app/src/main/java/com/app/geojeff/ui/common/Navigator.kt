package com.app.geojeff.ui.common

import android.content.Intent
import com.app.geojeff.data.entities.City
import com.app.geojeff.ui.cityDetail.CityDetailActivity
import com.app.geojeff.ui.searchHistory.SearchHistoryActivity
import com.app.geojeff.utils.Constants

class Navigator {

    fun goToHistory(activity: BaseActivity) {
        val intent = Intent(activity, SearchHistoryActivity::class.java)
        activity.startActivity(intent)
    }

    fun goToDetail(activity: BaseActivity, city: City?) {
        val intent = Intent(activity, CityDetailActivity::class.java)
        intent.apply {
            city?.let { putExtra(Constants.INTENT_EXTRA_CITY_DETAIL, city) }
        }
        activity.startActivity(intent)
    }

}
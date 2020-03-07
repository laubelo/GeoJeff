package com.app.geojeff.ui.common

import android.content.Intent
import com.app.geojeff.ui.searchHistory.SearchHistoryActivity

class Navigator {

    fun goToHistory(activity: BaseActivity) {
        val intent = Intent(activity, SearchHistoryActivity::class.java)
        activity.startActivity(intent)
    }

}
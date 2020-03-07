package com.app.geojeff.ui.searchHistory

import com.app.geojeff.R
import com.app.geojeff.ui.common.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class SearchHistoryActivity : BaseActivity(){

    override val viewModel by viewModel<SearchHistoryViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_search_history

    override fun getActionBarTitle(): String = getString(R.string.history_title)

    override fun displayBackButton(): Boolean = true

    override fun initView() {

    }

}

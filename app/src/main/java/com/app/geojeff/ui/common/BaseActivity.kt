package com.app.geojeff.ui.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.koin.android.ext.android.inject

/*
    BaseActivity controls all the Activity lifecycle methods and its ViewModel
 */

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val viewModel: BaseViewModel
    protected abstract fun getLayoutId(): Int
    protected abstract fun getActionBarTitle(): String
    protected abstract fun displayBackButton(): Boolean
    protected abstract fun initView()
    protected val navigator: Navigator by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()

        supportActionBar?.apply {
            title = getActionBarTitle()
            setDisplayHomeAsUpEnabled(displayBackButton())
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}
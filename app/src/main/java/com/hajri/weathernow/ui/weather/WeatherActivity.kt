package com.hajri.weathernow.ui.weather

import android.os.Bundle
import com.hajri.weathernow.R
import com.hajri.weathernow.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    override fun initView() {
        addFragment(containerId = weather_container.id, fragment = SearchCityFragment())
    }
}
package com.hajri.weathernow.ui.weather

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hajri.weathernow.R
import com.hajri.weathernow.data.network.ApiService
import com.hajri.weathernow.ui.base.BaseActivity
import com.hajri.weathernow.viewModels.CityViewModel
import com.hajri.weathernow.viewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : BaseActivity() {
    lateinit var cityViewModel: CityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModels()
        initView()
    }

    override fun initView() {
        addFragment(containerId = weather_container.id, fragment = SearchCityFragment())
    }

    /**
     * Init all WeatherActivity fragments' viewModels
     */
    private fun initViewModels() {
        cityViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(apiService = ApiService())
        ).get(CityViewModel::class.java)
    }
}
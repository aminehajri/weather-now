/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.ui.home

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.hajri.weathernow.R
import com.hajri.weathernow.data.models.City
import com.hajri.weathernow.data.network.ApiService
import com.hajri.weathernow.ui.base.BaseActivity
import com.hajri.weathernow.ui.city.SearchCityFragment
import com.hajri.weathernow.ui.weather.WeatherFragment
import com.hajri.weathernow.viewModels.CityViewModel
import com.hajri.weathernow.viewModels.WeatherViewModel
import com.hajri.weathernow.viewModelsFactory.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class WeatherActivity : BaseActivity() {
    lateinit var cityViewModel: CityViewModel
    lateinit var weatherViewModel: WeatherViewModel
    lateinit var selectedCity: City

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModels()
        initView()
    }

    override fun initView() {
        displayCityFragment()
    }


    override fun initViewModels() {
        cityViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(apiService = ApiService())
        ).get(CityViewModel::class.java)
        weatherViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(apiService = ApiService())
        ).get(WeatherViewModel::class.java)
    }

    fun displayWeatherFragment(selectedCity: City) {
        this.selectedCity = selectedCity
        replaceFragment(
            containerId = weather_container.id,
            fragment = WeatherFragment(),
            addToBackStack = true
        )
    }


    private fun displayCityFragment() {
        addFragment(containerId = weather_container.id, fragment = SearchCityFragment())
    }

}
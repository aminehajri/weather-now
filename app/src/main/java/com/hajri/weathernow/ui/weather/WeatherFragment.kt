/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.hajri.weathernow.R
import com.hajri.weathernow.data.models.Weather
import com.hajri.weathernow.ui.base.BaseFragment
import com.hajri.weathernow.utils.Status
import kotlinx.android.synthetic.main.fragment_weather.view.*
import java.util.*


class WeatherFragment : BaseFragment() {

    private lateinit var mView: View
    private lateinit var weatherAdapter: WeatherAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_weather, container, false)

        initView()
        getWeather()



        return mView
    }


    override fun initView() {
        setProgressBar(mView.progress_bar_weather)
        mView.apply {
            iv_back.setOnClickListener {
                weatherActivity.supportFragmentManager.popBackStack()
            }
            tv_toolbar_title.text = weatherActivity.selectedCity.title
        }
    }

    override fun <T> initObserver(param: T) {
        weatherActivity.weatherViewModel.getWeatherList(whereOnEarthId = param as Int).observe(
            this, Observer { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {

                        hideProgressBar()
                        resource.data?.let { response ->
                            response.body()?.let { weatherResponse ->
                                if (weatherResponse.consolidatedWeatherResponse!!.size > 0) {
                                    mView.tv_empty_weather.visibility = View.GONE
                                    mView.weather_recycler.visibility = View.VISIBLE
                                    updateWeatherRecycler(
                                        (
                                                weatherResponse.consolidatedWeatherResponse - weatherResponse.consolidatedWeatherResponse[0]
                                                )
                                                as ArrayList<Weather>
                                    )
                                    updateFreshWeatherItem(weatherResponse.consolidatedWeatherResponse[0])
                                } else {
                                    mView.weather_recycler.visibility = View.GONE
                                    mView.tv_empty_weather.visibility = View.VISIBLE
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        mView.tv_empty_weather.visibility = View.GONE
                        mView.weather_recycler.visibility = View.VISIBLE
                        hideProgressBar()
                        resource.message?.let { showToast(it) }
                    }
                    Status.LOADING -> {
                        showProgressBar()
                        mView.tv_empty_weather.visibility = View.GONE
                        mView.weather_recycler.visibility = View.GONE
                    }
                }
            }
        )
    }


    /**
     * Get weather from weatherViewModel
     * Update ui depending on connectivity status
     */
    private fun getWeather() {
        if (isOnline()) {
            mView.tv_empty_weather.apply {
                visibility = View.GONE
                text = getString(R.string.no_results_text)
            }
            initObserver(weatherActivity.selectedCity.whereOnEarthId)
        } else {
            mView.tv_empty_weather.apply {
                visibility = View.VISIBLE
                text = getString(R.string.no_internet_text)
            }
        }
    }

    /**
     * Update today's weather ui
     * @param weather
     */
    private fun updateFreshWeatherItem(weather: Weather) {
        mView.apply {
            Glide.with(context)
                .load(getString(R.string.fresh_status_image_path, weather.weatherStateAbbr))
                .into(iv_fresh_status)

            tv_fresh_status.text = weather.weatherStateName
            tv_fresh_temp_average.text = getString(R.string.temperature, weather.theTemp?.toInt())
            tv_fresh_highest_temp.text =
                getString(R.string.high_temperature, weather.maxTemp?.toInt())
            tv_fresh_lowest_temp.text =
                getString(R.string.low_temperature, weather.minTemp?.toInt())


        }
    }

    /**
     * Update next days' weather recycler
     * @param weatherList
     */
    private fun updateWeatherRecycler(weatherList: ArrayList<Weather>) {
        weatherAdapter = WeatherAdapter(weatherList)
        mView.apply {
            weather_recycler.apply {
                val mLayoutManager =
                    LinearLayoutManager(
                        weatherActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                layoutManager = mLayoutManager
                adapter = weatherAdapter
            }
        }
    }


}
package com.hajri.weathernow.ui.base

import android.content.Context
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.hajri.weathernow.ui.weather.WeatherActivity


open class BaseFragment : Fragment() {

    private var progressBar: ProgressBar? = null
    lateinit var weatherActivity: WeatherActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is WeatherActivity) {
            weatherActivity = context
        }
    }

    override fun onStop() {
        super.onStop()
        hideProgressBar()
    }

    /**
     * Bind progressBar
     * @param bar
     */
    fun setProgressBar(bar: ProgressBar) {
        progressBar = bar
    }

    /**
     * Show progressBar
     */
    fun showProgressBar() {
        progressBar?.visibility = View.VISIBLE
    }

    /**
     * Hide progressBar
     */
    fun hideProgressBar() {
        progressBar?.visibility = View.GONE
    }

    /**
     * Initialize view components
     */
    open fun initView() {}


}
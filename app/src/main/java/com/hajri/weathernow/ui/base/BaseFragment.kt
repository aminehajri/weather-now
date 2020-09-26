package com.hajri.weathernow.ui.base

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
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
     * Show toast
     * @param message
     */
    fun showToast(message: String) {
        Toast.makeText(
            activity,
            message,
            Toast.LENGTH_LONG
        ).show()
    }

    /**
     * Check if the device is online
     * @return isOnline
     */
    fun isOnline(): Boolean {
        val connectivityManager =
            activity?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    /**
     * Initialize view components
     */
    open fun initView() {}


}
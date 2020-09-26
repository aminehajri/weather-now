package com.hajri.weathernow.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import com.hajri.weathernow.R
import com.hajri.weathernow.ui.base.BaseActivity
import com.hajri.weathernow.ui.weather.WeatherActivity
import com.hajri.weathernow.utils.Constants

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initView()
    }

    override fun initView() {

        val intent = Intent(this, WeatherActivity::class.java)
        Handler().postDelayed({
            startActivity(intent)
            finish()
        }, Constants.SPLASH_TIME_OUT.toLong())
    }
}
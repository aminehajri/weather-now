package com.hajri.weathernow.callbacks

import com.hajri.weathernow.data.City

interface CityCallback {

    /**
     * Get selected city from CityAdapter
     * @param city
     */
    fun getSelectedCity(city: City)
}
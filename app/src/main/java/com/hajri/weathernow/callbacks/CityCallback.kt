package com.hajri.weathernow.callbacks

import com.hajri.weathernow.data.models.City

interface CityCallback {

    /**
     * Get selected city from CityAdapter
     * @param city
     */
    fun getSelectedCity(city: City)
}
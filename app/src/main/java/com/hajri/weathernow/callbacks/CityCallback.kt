/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.callbacks

import com.hajri.weathernow.data.models.City

interface CityCallback {

    /**
     * Get selected city from CityAdapter
     * @param city
     */
    fun getSelectedCity(city: City)
}
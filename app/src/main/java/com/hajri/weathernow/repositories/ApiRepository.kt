/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.repositories

import com.hajri.weathernow.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    /**
     * Get cities from apiService
     * @param cityName
     */
    suspend fun getCities(cityName: String) = apiService.getCities(cityName = cityName)


    /**
     * Get list of weather from apiService
     * @param whereOnEarthId
     */
    suspend fun getWeatherList(whereOnEarthId: String) =
        apiService.getWeatherList(whereOnEarthId = whereOnEarthId)
}
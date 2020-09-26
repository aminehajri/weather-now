package com.hajri.weathernow.repositories

import com.hajri.weathernow.data.network.ApiService

class ApiRepository(private val apiService: ApiService) {

    /**
     * Get cities from apiService
     * @param cityName
     *
     */
    suspend fun getCities(cityName: String) = apiService.getCities(cityName)
}
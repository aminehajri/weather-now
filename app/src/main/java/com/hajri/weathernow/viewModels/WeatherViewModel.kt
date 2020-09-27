/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hajri.weathernow.repositories.ApiRepository
import com.hajri.weathernow.utils.Resource
import kotlinx.coroutines.Dispatchers

class WeatherViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    /**
     * Get weather list from apiRepository
     * @param whereOnEarthId
     */
    fun getWeatherList(whereOnEarthId: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getWeatherList(whereOnEarthId = whereOnEarthId)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }

}
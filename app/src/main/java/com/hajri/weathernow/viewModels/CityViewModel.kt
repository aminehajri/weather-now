package com.hajri.weathernow.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hajri.weathernow.repositories.ApiRepository
import com.hajri.weathernow.utils.Resource
import kotlinx.coroutines.Dispatchers

class CityViewModel(private val apiRepository: ApiRepository) : ViewModel() {

    /**
     * Get cities form apiRepository
     * @param cityName
     */
    fun getCities(cityName: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiRepository.getCities(cityName)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }

    }
}
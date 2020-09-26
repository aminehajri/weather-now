package com.hajri.weathernow.viewModelsFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hajri.weathernow.data.network.ApiService
import com.hajri.weathernow.repositories.ApiRepository
import com.hajri.weathernow.viewModels.CityViewModel

class ViewModelFactory(private val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            return CityViewModel(ApiRepository(apiService = apiService)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}
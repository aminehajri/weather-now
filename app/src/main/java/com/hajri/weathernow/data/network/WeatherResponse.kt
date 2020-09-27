/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.data.network

import com.google.gson.annotations.SerializedName
import com.hajri.weathernow.data.models.Weather

data class WeatherResponse(
    @SerializedName("consolidated_weather")
    val consolidatedWeatherResponse: ArrayList<Weather>?

)

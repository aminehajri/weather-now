/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.data.models

import com.google.gson.annotations.SerializedName

data class City(
    @SerializedName("title")
    val title: String?,

    @SerializedName("location_type")
    val locationType: String?,

    @SerializedName("woeid")
    val whereOnEarthId: Int?,

    @SerializedName("latt_long")
    val latitudeLongitude: String?


)
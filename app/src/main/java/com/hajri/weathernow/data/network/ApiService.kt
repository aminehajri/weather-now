package com.hajri.weathernow.data.network

import com.hajri.weathernow.BuildConfig
import com.hajri.weathernow.data.models.City
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object {
        operator fun invoke(): ApiService {

            val okHttpClient = OkHttpClient.Builder()
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

    /**
     * Get cities from API
     */
    @GET("search")
    suspend fun getCities(@Query("query") cityName: String): Response<ArrayList<City>>
}
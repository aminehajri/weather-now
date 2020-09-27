/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.ui.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hajri.weathernow.R
import com.hajri.weathernow.callbacks.CityCallback
import com.hajri.weathernow.data.models.City
import kotlinx.android.synthetic.main.city_item.view.*

class CityAdapter(
    private val cities: ArrayList<City>,
    private val cityCallback: CityCallback
) :
    RecyclerView.Adapter<CityAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvCityName = view.tv_city_name
        private val tvCityCoordinates = view.tv_city_coordinates


        fun bind(city: City) {
            tvCityName.text = city.title
            tvCityCoordinates.text = city.latitudeLongitude

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.city_item, parent, false)

        )

    override fun getItemCount(): Int = cities.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(cities[position])
        holder.itemView.setOnClickListener {
            cityCallback.getSelectedCity(cities[position])
        }
    }
}
/*
 * Copyright (c) 2020. by Mohamed Amine Hajri
 */

package com.hajri.weathernow.ui.weather

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hajri.weathernow.R
import com.hajri.weathernow.data.models.Weather
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherAdapter(
    private val weatherList: ArrayList<Weather>
) :
    RecyclerView.Adapter<WeatherAdapter.MyViewHolder>() {


    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvDay = view.tv_day
        private val tvHighestTemp = view.tv_highest_temp
        private val tvLowestTemp = view.tv_lowest_temp
        private val ivStatus = view.iv_status


        fun bind(weather: Weather) {
            tvDay.text = weather.applicableDate
            tvHighestTemp.text = weather.maxTemp?.toInt().toString()
            tvLowestTemp.text = weather.minTemp?.toInt().toString()
            Glide.with(itemView)
                .load(
                    itemView.context.getString(
                        R.string.status_image_path,
                        weather.weatherStateAbbr
                    )
                )
                .into(ivStatus)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder = MyViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.weather_item, parent, false)

    )

    override fun getItemCount(): Int = weatherList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }
}
package com.hajri.weathernow.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hajri.weathernow.R
import com.hajri.weathernow.ui.base.BaseFragment


class SearchCityFragment : BaseFragment() {

    private lateinit var mView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_city, container, false)
        return mView
    }


}
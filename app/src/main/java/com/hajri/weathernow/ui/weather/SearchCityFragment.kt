package com.hajri.weathernow.ui.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hajri.weathernow.R
import com.hajri.weathernow.callbacks.CityCallback
import com.hajri.weathernow.data.models.City
import com.hajri.weathernow.ui.base.BaseFragment
import com.hajri.weathernow.utils.Status
import kotlinx.android.synthetic.main.fragment_search_city.view.*


class SearchCityFragment : BaseFragment(), CityCallback {

    private lateinit var mView: View
    private lateinit var cityAdapter: CityAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_search_city, container, false)
        initView()
        return mView
    }

    override fun initView() {

        setProgressBar(mView.progress_bar)
        mView.search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {

                p0?.let {
                    if (isOnline()) {
                        mView.tv_empty_list.apply {
                            visibility = View.GONE
                            text = getString(R.string.no_results_text)
                        }
                        initObserver(it)
                    } else {
                        mView.tv_empty_list.apply {
                            visibility = View.VISIBLE
                            text = getString(R.string.no_internet_text)
                        }
                    }

                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })


    }

    override fun getSelectedCity(city: City) {

    }


    /**
     * Observe cities and update ui
     * @param cityName
     */
    private fun initObserver(cityName: String) {

        weatherActivity.cityViewModel.getCities(cityName = cityName)
            .observe(this@SearchCityFragment,
                Observer { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {

                            hideProgressBar()
                            resource.data?.let { response ->
                                response.body()?.let {
                                    if (it.size > 0) {
                                        mView.tv_empty_list.visibility = View.GONE
                                        mView.city_recycler.visibility = View.VISIBLE
                                        updateCityRecycler(it)
                                    } else {
                                        mView.city_recycler.visibility = View.GONE
                                        mView.tv_empty_list.visibility = View.VISIBLE
                                    }
                                }
                            }
                        }
                        Status.ERROR -> {
                            mView.tv_empty_list.visibility = View.GONE
                            mView.city_recycler.visibility = View.VISIBLE
                            hideProgressBar()
                            resource.message?.let { showToast(it) }
                        }
                        Status.LOADING -> {
                            showProgressBar()
                            mView.tv_empty_list.visibility = View.GONE
                            mView.city_recycler.visibility = View.GONE
                        }
                    }
                }
            )

    }


    /**
     * Update city_recycler
     * @param cities
     */
    private fun updateCityRecycler(cities: ArrayList<City>) {
        cityAdapter = CityAdapter(
            cities,
            this
        )

        mView.apply {

            city_recycler.apply {
                val mLayoutManager =
                    LinearLayoutManager(
                        weatherActivity,
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                layoutManager = mLayoutManager
                adapter = cityAdapter
            }
        }
    }
}
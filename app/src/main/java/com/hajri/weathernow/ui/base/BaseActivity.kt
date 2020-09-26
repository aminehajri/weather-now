package com.hajri.weathernow.ui.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    /**
     * Add the first fragment to the activity
     * @param containerId
     * @param fragment
     */
    fun addFragment(
        containerId: Int,
        fragment: Fragment
    ) {
        supportFragmentManager.beginTransaction()
            .add(containerId, fragment)
            .commit()
    }

    /**
     * Replace existing fragment and add it to backStack if needed
     * @param containerId
     * @param fragment
     * @param addToBackStack
     */
    fun replaceFragment(
        containerId: Int,
        fragment: Fragment,
        addToBackStack: Boolean
    ) {
        with(supportFragmentManager.beginTransaction(), {
            replace(containerId, fragment)
            if (addToBackStack) {
                addToBackStack(fragment.tag)
            }
            commit()

        })
    }

    /**
     * Initialize view components
     */
    open fun initView() {}
}
package com.virginmoney.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class TabsPagerAdapter(fragment: FragmentActivity) :
    FragmentStateAdapter(fragment) {

    private val tabFragmentsCreators: MutableMap<Int, Fragment> = mutableMapOf()

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]!!
    }

    fun addFragment(position: Int, fragment: Fragment) {
        tabFragmentsCreators[position] = fragment
    }

}
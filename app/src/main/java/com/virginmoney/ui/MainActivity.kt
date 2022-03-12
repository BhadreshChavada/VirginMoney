package com.virginmoney.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.virginmoney.R
import com.virginmoney.databinding.ActivityMainBinding
import com.virginmoney.ui.adapter.TabsPagerAdapter
import androidx.fragment.app.FragmentActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setupTabMediator()
    }


    private fun setupTabMediator() {
        val userFragment = UserFragment()
        val roomFragment = RoomFragment()

        val pagerAdapter =
            TabsPagerAdapter(
                this
            )

        binding.pager.adapter = pagerAdapter
        binding.pager.isUserInputEnabled = false

        val tabs: MutableList<String> = mutableListOf()
        tabs.add(0, getString(R.string.user))
        tabs.add(1, getString(R.string.rooms))

        TabLayoutMediator(
            binding.tabLayout,
            binding.pager
        ) { tab: TabLayout.Tab, position: Int ->
            val view: View = LayoutInflater.from(this)
                .inflate(R.layout.layout_video_detail_tab_title, null)
            view.findViewById<TextView>(R.id.tvTabText).text = tabs[position]
            tab.customView = view
        }.attach()

        pagerAdapter.addFragment(0, userFragment)
        pagerAdapter.addFragment(1, roomFragment)
        pagerAdapter.notifyDataSetChanged()

    }
}
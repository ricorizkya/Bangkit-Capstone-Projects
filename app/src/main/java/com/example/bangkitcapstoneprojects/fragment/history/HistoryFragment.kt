package com.example.bangkitcapstoneprojects.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.adapter.SectionPagerHistoryAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryBinding
import com.google.android.material.tabs.TabLayoutMediator

class HistoryFragment : Fragment() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.donate_food,
                R.string.donate_money
        )
    }

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPagerConfig()
    }

    private fun viewPagerConfig() {
        val sectionPagerHistoryAdapter = SectionPagerHistoryAdapter(activity!!.applicationContext, activity!!)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerHistoryAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}
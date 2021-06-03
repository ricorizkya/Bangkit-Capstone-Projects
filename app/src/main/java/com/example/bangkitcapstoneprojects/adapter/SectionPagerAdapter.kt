package com.example.bangkitcapstoneprojects.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangkitcapstoneprojects.fragment.FoodFragment
import com.example.bangkitcapstoneprojects.fragment.MoneyFragment

class SectionPagerAdapter(private val context: Context, fragmentActivity: FragmentActivity)
    :FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FoodFragment()
            1 -> fragment = MoneyFragment()
        }
        return fragment as Fragment
    }
}
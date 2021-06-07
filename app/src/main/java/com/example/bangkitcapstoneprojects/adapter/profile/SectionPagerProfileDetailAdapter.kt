package com.example.bangkitcapstoneprojects.adapter.profile

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.bangkitcapstoneprojects.fragment.history.HistoryFoodFragment
import com.example.bangkitcapstoneprojects.fragment.history.HistoryMoneyFragment
import com.example.bangkitcapstoneprojects.fragment.profile.ProcessFragment

class SectionPagerDetailAdapter(private val context: Context, fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity){

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HistoryFoodFragment()
            1 -> fragment = HistoryMoneyFragment()
        }
        return fragment as Fragment
    }
}
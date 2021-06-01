package com.example.bangkitcapstoneprojects.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.adapter.EventAdapter
import com.example.bangkitcapstoneprojects.databinding.ActivityMainBinding
import com.example.bangkitcapstoneprojects.fragment.DashboardFragment
import com.example.bangkitcapstoneprojects.fragment.HistoryFragment
import com.example.bangkitcapstoneprojects.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dashboardFragment = DashboardFragment()
        val historyFragment = HistoryFragment()
        val profileFragment = ProfileFragment()

        makeCurrentFragment(dashboardFragment)
        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.dashboard_menu -> makeCurrentFragment(dashboardFragment)
                R.id.history_menu -> makeCurrentFragment(historyFragment)
                R.id.Profile_menu -> makeCurrentFragment(profileFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_content, fragment)
            commit()
        }
    }
}
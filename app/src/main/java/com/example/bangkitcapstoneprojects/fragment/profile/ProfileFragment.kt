package com.example.bangkitcapstoneprojects.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.activity.LoginActivity
import com.example.bangkitcapstoneprojects.adapter.SectionPagerHistoryAdapter
import com.example.bangkitcapstoneprojects.adapter.SectionPagerProfileAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
                R.string.process,
                R.string.done
        )
    }

    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        binding.username.text = currentUser?.displayName
        binding.tvEmail.text = currentUser?.email
        Glide.with(this)
            .load(currentUser?.photoUrl)
            .into(binding.imgAvatar)

        viewPagerConfig()

        binding.imgLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }
    }

    private fun viewPagerConfig() {
        val sectionPagerProfileAdapter = SectionPagerProfileAdapter(activity!!.applicationContext, activity!!)
        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = sectionPagerProfileAdapter
        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

}
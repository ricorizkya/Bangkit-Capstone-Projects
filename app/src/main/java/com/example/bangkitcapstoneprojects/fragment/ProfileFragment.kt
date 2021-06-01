package com.example.bangkitcapstoneprojects.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.activity.LoginActivity
import com.example.bangkitcapstoneprojects.adapter.EventAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryBinding
import com.example.bangkitcapstoneprojects.databinding.FragmentProfileBinding
import com.example.bangkitcapstoneprojects.viewModel.EventViewModel
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

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

        binding.imgLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(intent)
            activity?.finish()
        }

//        if (activity != null) {
//            val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[EventViewModel::class.java]
//            val event = viewModel.getEvent()
//            val eventAdapter = EventAdapter()
//            eventAdapter.setEvent(event)
//
//            with(binding.rvEvent) {
//                layoutManager = LinearLayoutManager(context)
//                setHasFixedSize(true)
//                adapter = eventAdapter
//            }
//        }
    }

}
package com.example.bangkitcapstoneprojects.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bangkitcapstoneprojects.adapter.EventAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryBinding
import com.example.bangkitcapstoneprojects.viewModel.EventViewModel

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
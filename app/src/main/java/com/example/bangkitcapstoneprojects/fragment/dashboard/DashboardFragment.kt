package com.example.bangkitcapstoneprojects.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstoneprojects.activity.MakeEventActivity
import com.example.bangkitcapstoneprojects.adapter.EventAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentDashboardBinding
import com.example.bangkitcapstoneprojects.model.Eventt
import com.google.firebase.database.*

class DashboardFragment : Fragment() {

    private lateinit var binding: FragmentDashboardBinding
    private lateinit var eventRecyclerView: RecyclerView
    private lateinit var eventArrayList: ArrayList<Eventt>
    private lateinit var ref: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {

            eventRecyclerView = binding.rvEvent
            eventRecyclerView.layoutManager = LinearLayoutManager(context)
            eventRecyclerView.setHasFixedSize(true)

            eventArrayList = arrayListOf<Eventt>()
            getEventData()

            binding.fabAdd.setOnClickListener {
                val intent = Intent(activity, MakeEventActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }

    fun getEventData() {
        ref = FirebaseDatabase.getInstance().getReference("event")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (eventSnapshot in snapshot.children) {
                        val event = eventSnapshot.getValue(Eventt::class.java)
                        eventArrayList.add(event!!)
                    }
                    eventRecyclerView.adapter = EventAdapter(eventArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
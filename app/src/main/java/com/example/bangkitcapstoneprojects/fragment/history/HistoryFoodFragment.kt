package com.example.bangkitcapstoneprojects.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstoneprojects.adapter.HistoryFoodAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryFoodBinding
import com.example.bangkitcapstoneprojects.model.DonateFood
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HistoryFoodFragment : Fragment() {

    private lateinit var binding: FragmentHistoryFoodBinding
    private lateinit var foodArrayList: ArrayList<DonateFood>
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var query: Query
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentHistoryFoodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            foodRecyclerView = binding.rvHistoryFood
            foodRecyclerView.layoutManager = LinearLayoutManager(context)
            foodRecyclerView.setHasFixedSize(true)

            foodArrayList = arrayListOf()
            getFoodData()
        }
    }

    fun getFoodData() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val email = currentUser?.email
        query = FirebaseDatabase.getInstance().getReference("donate")
                .orderByChild("emailUser_information")
                .equalTo("$email - food")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (eventSnapshot in snapshot.children) {
                        val donateFood = eventSnapshot.getValue(DonateFood::class.java)
                        foodArrayList.add(donateFood!!)
                    }
                    foodRecyclerView.adapter = HistoryFoodAdapter(foodArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
package com.example.bangkitcapstoneprojects.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstoneprojects.R
import com.example.bangkitcapstoneprojects.adapter.HistoryFoodAdapter
import com.example.bangkitcapstoneprojects.adapter.HistoryMoneyAdapter
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryFoodBinding
import com.example.bangkitcapstoneprojects.databinding.FragmentHistoryMoneyBinding
import com.example.bangkitcapstoneprojects.model.DonateFood
import com.example.bangkitcapstoneprojects.model.DonateMoney
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class HistoryMoneyFragment : Fragment() {

    private lateinit var binding: FragmentHistoryMoneyBinding
    private lateinit var moneyArrayList: ArrayList<DonateMoney>
    private lateinit var moneyRecyclerView: RecyclerView
    private lateinit var query: Query
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryMoneyBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            moneyRecyclerView = binding.rvHistoryMoney
            moneyRecyclerView.layoutManager = LinearLayoutManager(context)
            moneyRecyclerView.setHasFixedSize(true)

            moneyArrayList = arrayListOf()
            getDataMoney()
        }
    }

    fun getDataMoney() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        val email = currentUser?.email
        query = FirebaseDatabase.getInstance().getReference("donate")
                .orderByChild("emailUser_information")
                .equalTo("$email - money")
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (eventSnapshot in snapshot.children) {
                        val donateMoney = eventSnapshot.getValue(DonateMoney::class.java)
                        moneyArrayList.add(donateMoney!!)
                    }
                    moneyRecyclerView.adapter = HistoryMoneyAdapter(moneyArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}
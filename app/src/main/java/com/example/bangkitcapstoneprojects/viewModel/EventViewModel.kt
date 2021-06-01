package com.example.bangkitcapstoneprojects.viewModel

import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.bangkitcapstoneprojects.model.Eventt
import com.google.firebase.database.*

class EventViewModel: ViewModel() {

    private lateinit var ref: DatabaseReference
    private lateinit var eventArrayList: ArrayList<Eventt>
    private lateinit var eventRecyclerView: RecyclerView

    fun getEventData() {
        eventArrayList = arrayListOf<Eventt>()
        ref = FirebaseDatabase.getInstance().getReference("event")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (eventSnapshot in snapshot.children) {
                        val event = eventSnapshot.getValue(Eventt::class.java)
                        if (event != null) {
                            eventArrayList.add(event)
                        }
//                        eventRecyclerView.adapter = EventAdapter()
                    }
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    
}
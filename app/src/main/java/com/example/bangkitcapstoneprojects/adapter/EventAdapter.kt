package com.example.bangkitcapstoneprojects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.databinding.ItemListBinding
import com.example.bangkitcapstoneprojects.model.Eventt

class EventAdapter(private val eventList: ArrayList<Eventt>): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

//    private var listEvent = ArrayList<Eventt>()

    inner class EventViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Eventt) {
            with(binding) {
                tvTitle.text = event.title
                tvDesc.text = event.desc
                Glide.with(itemView.context)
                    .load(event.image)
                    .into(imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemListBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(itemListBinding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = eventList[position]
        holder.bind(event)
    }

    override fun getItemCount(): Int = eventList.size
}
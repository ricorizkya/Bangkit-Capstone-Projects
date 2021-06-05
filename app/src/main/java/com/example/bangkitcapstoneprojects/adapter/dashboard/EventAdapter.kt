package com.example.bangkitcapstoneprojects.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.activity.EventDetailActivity
import com.example.bangkitcapstoneprojects.databinding.ItemListBinding
import com.example.bangkitcapstoneprojects.fragment.FoodFragment
import com.example.bangkitcapstoneprojects.fragment.MoneyFragment
import com.example.bangkitcapstoneprojects.model.Eventt
import kotlin.coroutines.coroutineContext

class EventAdapter(private val eventList: ArrayList<Eventt>): RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(private val binding: ItemListBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Eventt) {
            with(binding) {
                tvTitle.text = event.title
                tvDesc.text = event.desc
                Glide.with(itemView.context)
                    .load(event.image)
                    .into(imgPoster)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, EventDetailActivity::class.java)
                    intent.putExtra(EventDetailActivity.EXTRA_EVENT, event.title)
                    intent.putExtra(EventDetailActivity.EXTRA_ID, event.id)
                    intent.putExtra(EventDetailActivity.EXTRA_EMAIL, event.email)
                    intent.putExtra(EventDetailActivity.EXTRA_USER, event.username)
                    intent.putExtra(EventDetailActivity.EXTRA_DESC, event.desc)
                    intent.putExtra(EventDetailActivity.EXTRA_DATE, event.date)
                    intent.putExtra(EventDetailActivity.EXTRA_FOOD, event.food)
                    intent.putExtra(EventDetailActivity.EXTRA_MONEY, event.money)
                    intent.putExtra(EventDetailActivity.EXTRA_IMAGE, event.image)
                    intent.putExtra(FoodFragment.EXTRA_ID, event.id)
                    intent.putExtra(FoodFragment.EXTRA_TITLE, event.title)
                    intent.putExtra(FoodFragment.EXTRA_IMAGE, event.image)
                    intent.putExtra(MoneyFragment.EXTRA_ID, event.id)
                    intent.putExtra(MoneyFragment.EXTRA_TITLE, event.title)
                    intent.putExtra(MoneyFragment.EXTRA_IMAGE, event.image)
                    itemView.context.startActivity(intent)
                }
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
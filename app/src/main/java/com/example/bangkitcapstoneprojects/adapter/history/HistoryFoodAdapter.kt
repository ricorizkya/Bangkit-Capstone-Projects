package com.example.bangkitcapstoneprojects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.databinding.ItemListHistoryFoodBinding
import com.example.bangkitcapstoneprojects.model.DonateFood

class HistoryFoodAdapter(private val foodList: ArrayList<DonateFood>): RecyclerView.Adapter<HistoryFoodAdapter.HistoryFoodViewHolder>() {

    inner class HistoryFoodViewHolder(private val binding: ItemListHistoryFoodBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(donateFood: DonateFood) {
            with(binding) {
                tvTitle.text = donateFood.titleEvent
                tvFood.text = donateFood.donateFood
                tvDate.text = donateFood.currentDate
                Glide.with(itemView.context)
                        .load(donateFood.imageEvent)
                        .into(binding.imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryFoodAdapter.HistoryFoodViewHolder {
        val itemListHistoryFoodBinding = ItemListHistoryFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryFoodViewHolder(itemListHistoryFoodBinding)
    }

    override fun onBindViewHolder(holder: HistoryFoodAdapter.HistoryFoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.bind(food)
    }

    override fun getItemCount(): Int = foodList.size

}
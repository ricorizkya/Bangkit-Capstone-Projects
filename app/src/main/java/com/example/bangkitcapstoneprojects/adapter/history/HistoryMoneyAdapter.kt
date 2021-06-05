package com.example.bangkitcapstoneprojects.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstoneprojects.databinding.ItemListHistoryFoodBinding
import com.example.bangkitcapstoneprojects.databinding.ItemListHistoryMoneyBinding
import com.example.bangkitcapstoneprojects.model.DonateMoney

class HistoryMoneyAdapter(private val moneyList: ArrayList<DonateMoney>): RecyclerView.Adapter<HistoryMoneyAdapter.HistoryMoneyVIewHolder>() {

    inner class HistoryMoneyVIewHolder(private val binding: ItemListHistoryMoneyBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(donateMoney: DonateMoney) {
            with(binding) {
                tvTitle.text = donateMoney.titleEvent
                tvMoney.text = donateMoney.donateMoney
                tvDate.text = donateMoney.currentDate
                Glide.with(itemView.context)
                        .load(donateMoney.imageEvent)
                        .into(binding.imgPoster)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryMoneyVIewHolder {
        val itemListHistoryMoneyBinding = ItemListHistoryMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryMoneyVIewHolder(itemListHistoryMoneyBinding)
    }

    override fun onBindViewHolder(holder: HistoryMoneyVIewHolder, position: Int) {
        val money = moneyList[position]
        holder.bind(money)
    }

    override fun getItemCount(): Int = moneyList.size
}
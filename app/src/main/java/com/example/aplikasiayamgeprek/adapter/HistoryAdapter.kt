package com.example.aplikasiayamgeprek.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiayamgeprek.R
import com.example.aplikasiayamgeprek.home.HistoryManager
import com.example.aplikasiayamgeprek.home.HistoryModel

class HistoryAdapter (
    private val listHistory: List<HistoryModel>,
    private val onBeliLagiClick: (HistoryModel) -> Unit
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {
    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgfood: ImageView = itemView.findViewById(R.id.imgHistoryFood)
        val tvTitle: TextView = itemView.findViewById(R.id.tvHistoryTitle)
        val tvSubtitle: TextView = itemView.findViewById(R.id.tvHistorySubtitle)
        val tvUnitPrice: TextView = itemView.findViewById(R.id.tvHistoryUnitPrice)
        val tvTotalPrice: TextView = itemView.findViewById(R.id.tvHistoryTotalPrice)
        val tvStatus: TextView = itemView.findViewById(R.id.tvHistoryStatus)
        val btnBeliLagi: Button = itemView.findViewById(R.id.btnBeliLagi)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = listHistory[position]

        holder.imgfood.setImageResource(item.image)
        holder.tvTitle.text = item.title
        holder.tvSubtitle.text = item.subtitle
        holder.tvUnitPrice.text = "Harga satuan  ${item.unitPrice}"
        holder.tvTotalPrice.text = "Total harga  ${item.totalPrice}"
        holder.tvStatus.text = item.status

        holder.btnBeliLagi.setOnClickListener{
            onBeliLagiClick(item)
        }
    }

    override fun getItemCount(): Int = listHistory.size
        }

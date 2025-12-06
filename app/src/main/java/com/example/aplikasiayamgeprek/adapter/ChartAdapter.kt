package com.example.aplikasiayamgeprek.adapter

import com.example.aplikasiayamgeprek.home.ChartModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiayamgeprek.R
import com.example.aplikasiayamgeprek.home.ChartManager


class   ChartAdapter(
    private val cartItems: MutableList<ChartModel>,
    private val onUpdate: () -> Unit
) : RecyclerView.Adapter<ChartAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.findViewById<ImageView>(R.id.imgCart)
        val name = itemView.findViewById<TextView>(R.id.txtName)
        val desc = itemView.findViewById<TextView>(R.id.txtDesc)
        val price = itemView.findViewById<TextView>(R.id.txtPrice)
        val qty = itemView.findViewById<TextView>(R.id.txtQty)
        val btnPlus = itemView.findViewById<TextView>(R.id.btnPlus)
        val btnMinus = itemView.findViewById<TextView>(R.id.btnMinus)
        val btnDelete = itemView.findViewById<ImageView>(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_chart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = cartItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = cartItems[position]

        holder.img.setImageResource(item.image)
        holder.name.text = item.name
        holder.desc.text = item.desc
        holder.price.text = "Rp.${item.price}"
        holder.qty.text = item.qty.toString()

        holder.btnPlus.setOnClickListener {
            ChartManager.increase(item)
            notifyItemChanged(position)
            onUpdate()
        }

        holder.btnMinus.setOnClickListener {
            ChartManager.decrease(item)
            notifyItemChanged(position)
            onUpdate()
        }

        holder.btnDelete.setOnClickListener {
            ChartManager.removeItem(item)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, cartItems.size)
            onUpdate()
        }
    }
}

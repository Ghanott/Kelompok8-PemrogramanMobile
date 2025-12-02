package com.example.aplikasiayamgeprek.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiayamgeprek.R

class MenuAdapter(private val data: List<MenuModel>) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imgMenu: ImageView = itemView.findViewById<ImageView>(R.id.imgMenu)
        val tvName: TextView = itemView.findViewById<TextView>(R.id.tvName)
        val tvPrice: TextView = itemView.findViewById<TextView>(R.id.tvPrice)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_menu, parent, false)

        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = data[position]

        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        holder.imgMenu.setImageResource(item.image)
    }

    override fun getItemCount(): Int = data.size

}
package com.example.aplikasiayamgeprek.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.aplikasiayamgeprek.R
import com.example.aplikasiayamgeprek.home.MenuModel

class MenuAdapter(
    private var listMenu: List<MenuModel>
) : RecyclerView.Adapter<MenuAdapter.MenuViewHolder>(){

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgFood: ImageView = itemView.findViewById(R.id.imgFood)
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int)
    : MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val item = listMenu[position]
        holder.tvName.text = item.name
        holder.tvPrice.text = item.price
        holder.imgFood.setImageResource(item.image)
    }

    override fun getItemCount(): Int = listMenu.size

    fun submitList(neList: List<MenuModel>) {
        listMenu = neList
        notifyDataSetChanged()
    }
}




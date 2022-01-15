package com.example.storeclient.adapter

import android.graphics.Color

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.storeclient.R
import com.example.storeclient.model.BestSellerItem
import com.example.storeclient.model.HotSalesItem
import com.example.storeclient.model.ProductDetails
import com.example.storeclient.model.ProductDetailsItem

class RecyclerViewCapacityAdapter(var context: Context, val click: () -> Unit) : RecyclerView.Adapter<RecyclerViewCapacityAdapter.ViewHolder>(){

    var capacityList : MutableList<String> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val capacityView: Button = itemView.findViewById(R.id.capacityPD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_capacity_pd, parent, false)
        return RecyclerViewCapacityAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.capacityView.setOnClickListener {
            click.invoke()
            holder.capacityView.setBackgroundColor(Color.parseColor("#FF6E4E"))
            holder.capacityView.setTextColor(Color.parseColor("#FFFFFF"))
        }
        if(position == 0){
            holder.capacityView.setBackgroundColor(Color.parseColor("#FF6E4E"))
            holder.capacityView.setTextColor(Color.parseColor("#FFFFFF"))
        }
        holder.capacityView.text = capacityList[position]
    }

    override fun getItemCount(): Int {
        return capacityList.size
    }
}
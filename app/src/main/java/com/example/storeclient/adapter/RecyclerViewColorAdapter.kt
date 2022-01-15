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

class RecyclerViewColorAdapter(var context: Context, val click: () -> Unit) : RecyclerView.Adapter<RecyclerViewColorAdapter.ViewHolder>(){

    var colorsList : MutableList<String> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val colorView: Button = itemView.findViewById(R.id.colorPD)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_color_pd, parent, false)
        return RecyclerViewColorAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorView.setOnClickListener {
            click.invoke()
            holder.colorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_selected, 0, 0)
        }
        if(position == 0){
            holder.colorView.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_selected, 0, 0)
        }
        holder.colorView.setBackgroundColor(Color.parseColor(colorsList[position]))
    }

    override fun getItemCount(): Int {
        return colorsList.size
    }
}
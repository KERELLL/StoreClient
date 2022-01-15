package com.example.storeclient.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class ViewPagerDetailsAdapter(var context: Context) : RecyclerView.Adapter<ViewPagerDetailsAdapter.ViewHolder>(){

    var productDetailsItemList : MutableList<String> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val productDetailsBackground: ImageView = itemView.findViewById(R.id.imageProductDetails)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product_details, parent, false)
        return ViewPagerDetailsAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        for (i in productDetailsItemList.indices){
            Glide.with(context).load(productDetailsItemList[position]).into(holder.productDetailsBackground)
        }
    }

    override fun getItemCount(): Int {
        return productDetailsItemList.size
    }
}
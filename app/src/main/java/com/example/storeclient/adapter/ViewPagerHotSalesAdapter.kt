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

class ViewPagerHotSalesAdapter(var context: Context) : RecyclerView.Adapter<ViewPagerHotSalesAdapter.ViewHolder>(){

    var hotSalesItemList : MutableList<HotSalesItem> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val hotSalesTitle: TextView = itemView.findViewById(R.id.hotSalesTitle)
        val hotSalesSubtitle: TextView = itemView.findViewById(R.id.hotSalesSubtitle)
        val hotSalesIsNewBackground: ImageView = itemView.findViewById(R.id.hotSalesIsNew)
        val hotSalesBackground: ImageView = itemView.findViewById(R.id.hotSalesPicture)
        val hotSalesIsNewText: TextView = itemView.findViewById(R.id.textIsNew)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_hot_sales, parent, false)
        return ViewPagerHotSalesAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(hotSalesItemList[position].hotSalesBackground).into(holder.hotSalesBackground)
        holder.hotSalesTitle.text = hotSalesItemList[position].hotSalesTitle
        holder.hotSalesSubtitle.text = hotSalesItemList[position].hotSalesSubtitle
        if(hotSalesItemList[position].hotSalesIsNewBackground){
            holder.hotSalesIsNewText.text = "New"
        }
        else{
            holder.hotSalesIsNewBackground.visibility = View.INVISIBLE
        }
    }

    override fun getItemCount(): Int {
        return hotSalesItemList.size
    }
}
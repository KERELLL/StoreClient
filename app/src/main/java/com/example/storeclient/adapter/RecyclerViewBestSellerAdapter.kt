package com.example.storeclient.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storeclient.R
import com.example.storeclient.model.BestSellerItem

class RecyclerViewBestSellerAdapter(private val context: Context, val click: (BestSellerItem) -> Unit) :
    RecyclerView.Adapter<RecyclerViewBestSellerAdapter.ViewHolder>() {

    var bestSellerList : MutableList<BestSellerItem> = mutableListOf()
    set(value){
        field = value
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val pictureBestSeller: ImageView = itemView.findViewById(R.id.bestSellerImageView)
        val priceWithoutDiscountBestSeller: TextView = itemView.findViewById(R.id.bestSellerWithoutDiscountPrice)
        val titleBestSeller: TextView = itemView.findViewById(R.id.bestSellerTitle)
        val priceDiscountBestSeller: TextView = itemView.findViewById(R.id.bestSellerDiscountPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_best_seller, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(bestSellerList[position].bestSellerBackground).into(holder.pictureBestSeller)
        holder.titleBestSeller.text = bestSellerList[position].bestSellerTitle
        holder.priceWithoutDiscountBestSeller.text = "$" + bestSellerList[position].priceWithoutDiscount.toString()
        holder.priceDiscountBestSeller.text = "$" + bestSellerList[position].discountPrice.toString()
        holder.itemView.setOnClickListener{
            click.invoke(BestSellerItem(holder.titleBestSeller.text as String,
                (holder.priceWithoutDiscountBestSeller.text as String).split("$")[1].toInt(),
                (holder.priceDiscountBestSeller.text as String).split("$")[1].toInt(),
                holder.pictureBestSeller.toString()))
        }
    }

    override fun getItemCount(): Int {
        return bestSellerList.size
    }
}
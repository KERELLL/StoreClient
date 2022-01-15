package com.example.storeclient.adapter


import android.annotation.SuppressLint
import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.storeclient.R
import com.example.storeclient.data.cartData.Basket
import com.example.storeclient.model.BestSellerItem

class RecyclerViewBasketAdapter(private val context: Context) :
    RecyclerView.Adapter<RecyclerViewBasketAdapter.ViewHolder>() {

    var basketList : MutableList<Basket> = mutableListOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val basketImage: ImageView = itemView.findViewById(R.id.basketImageView)
        val basketName: TextView = itemView.findViewById(R.id.basketNameTextView)
        val basketPrice: TextView = itemView.findViewById(R.id.basketPriceTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_basket, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(basketList[position].images).into(holder.basketImage)
        holder.basketName.text = basketList[position].title
        holder.basketPrice.text = "$" + basketList[position].price.toString()
    }

    override fun getItemCount(): Int {
        return basketList.size
    }
}
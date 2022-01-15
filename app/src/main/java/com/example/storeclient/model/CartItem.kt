package com.example.storeclient.model

import com.example.storeclient.data.cartData.Basket

class CartItem (
    val delivery : String,
    val total : Int,
    val basket: List<Basket>
)
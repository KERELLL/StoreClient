package com.example.storeclient.data.cartData

data class CartResponseItem(
    val _id: String,
    val basket: List<Basket>,
    val delivery: String,
    val total: Int
)
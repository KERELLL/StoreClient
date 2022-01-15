package com.example.storeclient.model

data class BestSellerItem(
    val bestSellerTitle: String,
    val priceWithoutDiscount: Int,
    val discountPrice: Int,
    val bestSellerBackground: String
)
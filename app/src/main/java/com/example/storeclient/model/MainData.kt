package com.example.storeclient.model

data class MainData(
    val hotSalesItems: MutableList<HotSalesItem>,
    val bestSellerItems: MutableList<BestSellerItem>
)
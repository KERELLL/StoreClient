package com.example.storeclient.data.mainData

data class MainDataResponseItem(
    val _id: String,
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)
package com.example.storeclient.model

data class ProductDetailsItem(
    val images: List<String>,
    val title: String,
    val rating: Int,
    val CPU: String,
    val camera: String,
    val sd: String,
    val ssd: String,
    val price: Int,
    val colors: List<String>,
    val capacity: List<String>
)


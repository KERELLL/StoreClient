package com.example.storeclient.repository

import com.example.storeclient.data.cartData.CartResponse

interface CartRepository {
    suspend fun  getCart() : CartResponse
}
package com.example.storeclient.repository

import com.example.storeclient.data.api.ApiBuilderCart
import com.example.storeclient.data.api.ApiBuilderProductDetails
import com.example.storeclient.data.cartData.CartResponse
import com.example.storeclient.data.productDetailsData.ProductDetailsResponse

object CartRepositoryImpl : CartRepository {
    private val cartService = ApiBuilderCart.apiServiceCart

    override suspend fun getCart(): CartResponse {
        return cartService.getCart()
    }
}
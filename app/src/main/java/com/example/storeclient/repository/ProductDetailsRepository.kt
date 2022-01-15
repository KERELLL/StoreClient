package com.example.storeclient.repository

import com.example.storeclient.data.mainData.MainDataResponse
import com.example.storeclient.data.productDetailsData.ProductDetailsResponse

interface ProductDetailsRepository {
    suspend fun  getProductDetails() : ProductDetailsResponse
}
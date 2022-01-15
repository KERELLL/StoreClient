package com.example.storeclient.data.api

import com.example.storeclient.data.productDetailsData.ProductDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiServiceProductDetails {
    @GET("detail")
    @Headers( "x-apikey: 61ddae2e95cb716ea5ee48e4")
    suspend fun getProductDetails() : ProductDetailsResponse
}
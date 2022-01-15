package com.example.storeclient.repository

import com.example.storeclient.data.api.ApiBuilder
import com.example.storeclient.data.api.ApiBuilderProductDetails
import com.example.storeclient.data.mainData.MainDataResponse
import com.example.storeclient.data.productDetailsData.ProductDetailsResponse

object ProductDetailsRepositoryImp: ProductDetailsRepository {
    private val productDetailsService = ApiBuilderProductDetails.apiServiceProductDetails

    override suspend fun getProductDetails(): ProductDetailsResponse {
        return productDetailsService.getProductDetails()
    }
}
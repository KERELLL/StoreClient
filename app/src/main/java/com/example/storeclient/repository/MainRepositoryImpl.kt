package com.example.storeclient.repository

import com.example.storeclient.data.api.ApiBuilder
import com.example.storeclient.data.mainData.MainDataResponse

object MainRepositoryImpl : MainRepository {
    private val mainService = ApiBuilder.apiService

    override suspend fun getMainDocuments(): MainDataResponse {
        return mainService.getMainDocuments();
    }
}
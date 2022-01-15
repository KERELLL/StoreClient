package com.example.storeclient.data.api

import com.example.storeclient.data.mainData.MainDataResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("home?apikey=61ddae2e95cb716ea5ee48e4")
    suspend fun getMainDocuments() : MainDataResponse
}
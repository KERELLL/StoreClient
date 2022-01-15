package com.example.storeclient.repository

import com.example.storeclient.data.mainData.MainDataResponse

interface MainRepository {

    suspend fun getMainDocuments() : MainDataResponse
}
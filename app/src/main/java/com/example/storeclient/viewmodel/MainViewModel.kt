package com.example.storeclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeclient.converter.MainConverter
import com.example.storeclient.model.MainData
import com.example.storeclient.repository.MainRepository
import com.example.storeclient.repository.MainRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val mainRepository: MainRepository = MainRepositoryImpl
    private val mainStateFlow: MutableStateFlow<UIStateMainDocuments> =
        MutableStateFlow(UIStateMainDocuments.Loading)
    val mainStateFlowPublic = mainStateFlow.asStateFlow()

    fun getMainDocuments() {
            viewModelScope.launch {
                try{
                    val response = withContext(Dispatchers.IO) {
                        mainRepository.getMainDocuments()
                    }
                    val hotSalesItem = MainConverter().convert(response)
                    mainStateFlow.value = UIStateMainDocuments.Success(hotSalesItem)
                }catch (e: Exception){
                    mainStateFlow.value = UIStateMainDocuments.Error(e)
                }
        }
    }
}

sealed class UIStateMainDocuments {
    object Loading : UIStateMainDocuments()
    class Error(val e: Exception) : UIStateMainDocuments()
    class Success(val hotSalesItem: MainData) : UIStateMainDocuments()
}
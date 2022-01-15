package com.example.storeclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeclient.converter.MainConverter
import com.example.storeclient.model.BestSellerItem
import com.example.storeclient.model.MainData
import com.example.storeclient.model.ProductDetails
import com.example.storeclient.model.ProductDetailsItem
import com.example.storeclient.repository.MainRepository
import com.example.storeclient.repository.MainRepositoryImpl
import com.example.storeclient.repository.ProductDetailsRepository
import com.example.storeclient.repository.ProductDetailsRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductDetailsViewModel : ViewModel() {
    private val productDetailsRepository: ProductDetailsRepository = ProductDetailsRepositoryImp
    private val detailsStateFlow: MutableStateFlow<UIStateProductDetails> =
        MutableStateFlow(UIStateProductDetails.Loading)
    val detailsStateFlowPublic = detailsStateFlow.asStateFlow()

    fun getProductDetails() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    productDetailsRepository.getProductDetails()
                }
                val listOfDetailsItems = mutableListOf<ProductDetailsItem>()
                for(i in 0..1){
                    listOfDetailsItems.add(
                        ProductDetailsItem(response[0].images,
                            response[0].title,
                            response[0].rating,
                            response[0].CPU,
                            response[0].camera,
                            response[0].ssd,
                            response[0].sd,
                            response[0].price,
                            response[0].color,
                            response[0].capacity)
                    )
                }
                detailsStateFlow.value = UIStateProductDetails.Success(ProductDetails(listOfDetailsItems))
            } catch (e: Exception) {
                detailsStateFlow.value = UIStateProductDetails.Error(e)
            }
        }
    }
}

sealed class UIStateProductDetails {
    object Loading : UIStateProductDetails()
    class Error(val e: Exception) : UIStateProductDetails()
    class Success(val productDetails: ProductDetails) : UIStateProductDetails()
}
package com.example.storeclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storeclient.data.cartData.Basket
import com.example.storeclient.model.Cart
import com.example.storeclient.model.CartItem
import com.example.storeclient.model.ProductDetails
import com.example.storeclient.model.ProductDetailsItem
import com.example.storeclient.repository.CartRepository
import com.example.storeclient.repository.CartRepositoryImpl
import com.example.storeclient.repository.ProductDetailsRepository
import com.example.storeclient.repository.ProductDetailsRepositoryImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel : ViewModel() {
    private val cartRepository: CartRepository = CartRepositoryImpl
    private val cartStateFlow: MutableStateFlow<UIStateCart> =
        MutableStateFlow(UIStateCart.Loading)
    val cartStateFlowPublic = cartStateFlow.asStateFlow()

    fun getCart() {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    cartRepository.getCart()
                }
                val listOfCartItems = mutableListOf<CartItem>()
                val listOfBasketItem = mutableListOf<Basket>()
                for (i in response[0].basket.indices) {
                    listOfBasketItem.add(
                        Basket(
                            response[0].basket[i].id,
                            response[0].basket[i].images,
                            response[0].basket[i].price,
                            response[0].basket[i].title
                        )
                    )
                }
                for (i in 0..1) {
                    listOfCartItems.add(
                        CartItem(
                            response[0].delivery,
                            response[0].total,
                            listOfBasketItem
                        )
                    )
                }
                cartStateFlow.value = UIStateCart.Success(Cart(listOfCartItems))
            } catch (e: Exception) {
                cartStateFlow.value = UIStateCart.Error(e)
            }
        }
    }
}

sealed class UIStateCart {
    object Loading : UIStateCart()
    class Error(val e: Exception) : UIStateCart()
    class Success(val cart: Cart) : UIStateCart()
}
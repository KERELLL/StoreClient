package com.example.storeclient

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.storeclient.adapter.RecyclerViewBasketAdapter
import com.example.storeclient.adapter.RecyclerViewCapacityAdapter
import com.example.storeclient.databinding.ActivityCartBinding
import com.example.storeclient.databinding.ActivityProductDetailsBinding
import com.example.storeclient.viewmodel.CartViewModel
import com.example.storeclient.viewmodel.ProductDetailsViewModel
import com.example.storeclient.viewmodel.UIStateCart
import com.example.storeclient.viewmodel.UIStateProductDetails
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class CartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var cartViewModel: CartViewModel
    private lateinit var basketAdapter: RecyclerViewBasketAdapter
    private lateinit var layoutManager: LinearLayoutManager
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        cartViewModel.cartStateFlowPublic.onEach {
            when (it) {
                is UIStateCart.Loading -> {
                    Toast.makeText(this, "Data is loading", Toast.LENGTH_LONG).show()
                }
                is UIStateCart.Success -> {
                    basketAdapter.basketList.clear()
                    for(element in it.cart.cartItem[0].basket){
                        basketAdapter.basketList.add(
                            element
                        )
                    }
                    basketAdapter.notifyDataSetChanged()
                    binding.deliveryPriceView.text = it.cart.cartItem[0].delivery
                    binding.totalPriceView.text = "$" + it.cart.cartItem[0].total.toString() + " us"
                }
                is UIStateCart.Error -> Toast.makeText(
                    this,
                    it.e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }.launchIn(lifecycleScope)
        basketAdapter = RecyclerViewBasketAdapter(this)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewBasket.layoutManager = layoutManager
        binding.recyclerViewBasket.adapter = basketAdapter
        cartViewModel.getCart()
    }

}
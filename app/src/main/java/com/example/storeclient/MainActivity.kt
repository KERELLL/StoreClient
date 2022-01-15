package com.example.storeclient

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Visibility
import android.view.LayoutInflater
import android.view.View
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import com.example.storeclient.databinding.ActivityMainBinding
import com.example.storeclient.viewmodel.MainViewModel
import com.example.storeclient.viewmodel.UIStateMainDocuments
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.storeclient.adapter.RecyclerViewBestSellerAdapter
import com.example.storeclient.adapter.ViewPagerDetailsAdapter
import com.example.storeclient.adapter.ViewPagerHotSalesAdapter
import com.example.storeclient.model.BestSellerItem
import com.example.storeclient.model.ProductDetails
import com.example.storeclient.viewmodel.ProductDetailsViewModel
import com.example.storeclient.viewmodel.UIStateProductDetails
import com.google.android.material.bottomsheet.BottomSheetBehavior

class MainActivity : AppCompatActivity() {
    private lateinit var mainViewModel: MainViewModel


    private lateinit var binding : ActivityMainBinding
    private lateinit var bestSellerAdapter: RecyclerViewBestSellerAdapter
    private lateinit var hotSalesAdapter: ViewPagerHotSalesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.mainStateFlowPublic.onEach {
            when(it){
                is UIStateMainDocuments.Loading ->{
                    Toast.makeText(this, "Data is loading", Toast.LENGTH_LONG).show()
                }
                is UIStateMainDocuments.Success -> {
                    hotSalesAdapter.hotSalesItemList.clear()
                    for(i in 0 until it.hotSalesItem.hotSalesItems.size){
                        hotSalesAdapter.hotSalesItemList.add(
                            it.hotSalesItem.hotSalesItems[i]
                        )
                    }
                    hotSalesAdapter.notifyDataSetChanged()
                    bestSellerAdapter.bestSellerList.clear()
                    for (i in 0 until it.hotSalesItem.bestSellerItems.size){
                        bestSellerAdapter.bestSellerList.add(
                            it.hotSalesItem.bestSellerItems[i]
                        )
                    }
                    bestSellerAdapter.notifyDataSetChanged()

                }
                is UIStateMainDocuments.Error -> Toast.makeText(this,it.e.message, Toast.LENGTH_LONG).show()
            }
        }.launchIn(lifecycleScope)

        hotSalesAdapter = ViewPagerHotSalesAdapter(this)
        binding.viewPagerHotSales.adapter = hotSalesAdapter
        var position : BestSellerItem
        bestSellerAdapter = RecyclerViewBestSellerAdapter(this){
            val x = Intent(this, ProductDetailsActivity::class.java)
            startActivity(x)
            position = it
        }
        val gridLayoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        binding.recyclerViewBestSeller.layoutManager = gridLayoutManager
        binding.recyclerViewBestSeller.adapter = bestSellerAdapter
        mainViewModel.getMainDocuments()


        val brandArray = arrayOf("Samsung", "Iphone")
        val priceArray = arrayOf("$300-$500")
        val sizeArray = arrayOf("4.5 to 5.5 inches")
        val exposedMenuAdapter = ArrayAdapter(this, R.layout.item_brand_filter, brandArray)
        val exposedMenuAdapterPrice = ArrayAdapter(this, R.layout.item_brand_filter, priceArray)
        val exposedMenuAdapterSize = ArrayAdapter(this, R.layout.item_brand_filter, sizeArray)

        with(binding.brandFilter){
            setAdapter(exposedMenuAdapter)
        }
        with(binding.priceFilter){
            setAdapter(exposedMenuAdapterPrice)
        }
        with(binding.sizeFilter){
            setAdapter(exposedMenuAdapterSize)
        }

        val bottomSheetRoot = binding.bottomSheetRoot
        binding.openBottomSheetFilter.setOnClickListener {
            binding.bottomSheetRoot.visibility = View.VISIBLE
            val mBottomBehavior =
                BottomSheetBehavior.from(bottomSheetRoot)
            mBottomBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }

    }
}
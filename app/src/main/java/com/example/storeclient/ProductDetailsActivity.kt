package com.example.storeclient

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.size
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.storeclient.adapter.RecyclerViewCapacityAdapter
import com.example.storeclient.adapter.RecyclerViewColorAdapter
import com.example.storeclient.adapter.ViewPagerDetailsAdapter
import com.example.storeclient.adapter.ViewPagerHotSalesAdapter
import com.example.storeclient.databinding.ActivityMainBinding
import com.example.storeclient.databinding.ActivityProductDetailsBinding
import com.example.storeclient.viewmodel.MainViewModel
import com.example.storeclient.viewmodel.ProductDetailsViewModel
import com.example.storeclient.viewmodel.UIStateProductDetails
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ProductDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailsBinding
    private lateinit var productDetailsAdapter: ViewPagerDetailsAdapter
    private lateinit var colorAdapter: RecyclerViewColorAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var layoutManagerCapacity: LinearLayoutManager
    private lateinit var capacityAdapter: RecyclerViewCapacityAdapter
    private lateinit var detailsViewModel: ProductDetailsViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        detailsViewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        detailsViewModel.detailsStateFlowPublic.onEach {
            when (it) {
                is UIStateProductDetails.Loading -> {
                    Toast.makeText(this, "Data is loading", Toast.LENGTH_LONG).show()
                }
                is UIStateProductDetails.Success -> {
                    binding.productDetailsRating.rating =
                        it.productDetails.productDetailsItem[0].rating.toFloat()
                    binding.productDetailsTitle.text = it.productDetails.productDetailsItem[0].title
                    binding.cameraPD.text = it.productDetails.productDetailsItem[0].camera
                    binding.ramProductDetail.text = it.productDetails.productDetailsItem[0].CPU
                    binding.ssdPD.text = it.productDetails.productDetailsItem[0].ssd
                    binding.sdPD.text = it.productDetails.productDetailsItem[0].sd
                    binding.addToCartBtn.text =
                        "Add to Cart \t\t\t $" + it.productDetails.productDetailsItem[0].price
                    productDetailsAdapter.productDetailsItemList.clear()
                    for (i in it.productDetails.productDetailsItem[0].images.indices) {
                        productDetailsAdapter.productDetailsItemList.add(
                            it.productDetails.productDetailsItem[0].images[i]
                        )
                    }
                    productDetailsAdapter.notifyDataSetChanged()

                    colorAdapter.colorsList.clear()
                    for (i in it.productDetails.productDetailsItem[0].colors.indices) {
                        colorAdapter.colorsList.add(
                            it.productDetails.productDetailsItem[0].colors[i]
                        )
                    }
                    colorAdapter.notifyDataSetChanged()

                    capacityAdapter.capacityList.clear()
                    for (i in it.productDetails.productDetailsItem[0].capacity.indices) {
                        capacityAdapter.capacityList.add(
                            it.productDetails.productDetailsItem[0].capacity[i]
                        )
                    }
                    capacityAdapter.notifyDataSetChanged()
                }
                is UIStateProductDetails.Error -> Toast.makeText(
                    this,
                    it.e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }.launchIn(lifecycleScope)
        productDetailsAdapter = ViewPagerDetailsAdapter(this)
        binding.viewPagerProductDetails.clipToPadding = false
        binding.viewPagerProductDetails.clipChildren = false
        binding.viewPagerProductDetails.offscreenPageLimit = 3
        binding.viewPagerProductDetails.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        binding.viewPagerProductDetails.adapter = productDetailsAdapter
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        layoutManagerCapacity = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        colorAdapter = RecyclerViewColorAdapter(this) {
            for (i in 0 until colorAdapter.itemCount) {
                val view: Button =
                    ((binding.recyclerViewColor.getChildAt(i) as RelativeLayout).getChildAt(0) as CardView).getChildAt(
                        0
                    ) as Button
                view.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
            }
        }
        capacityAdapter = RecyclerViewCapacityAdapter(this) {
            for (i in 0 until capacityAdapter.itemCount) {
                val view: Button =
                    ((binding.recyclerViewCapacity.getChildAt(i) as RelativeLayout).getChildAt(0) as CardView).getChildAt(
                        0
                    ) as Button
                view.setBackgroundColor(Color.parseColor("#FFFFFF"))
                view.setTextColor(Color.parseColor("#8D8D8D"))
            }
        }

        binding.recyclerViewColor.layoutManager = layoutManager
        binding.recyclerViewColor.adapter = colorAdapter

        binding.recyclerViewCapacity.layoutManager = layoutManagerCapacity
        binding.recyclerViewCapacity.adapter = capacityAdapter
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(8))
        transformer.addTransformer(object : ViewPager2.PageTransformer {
            override fun transformPage(page: View, position: Float) {
                val v = 1 - Math.abs(position)
                page.scaleY = 0.8f + v * 0.2f
            }
        }
        )
        binding.viewPagerProductDetails.setPageTransformer(transformer)
        detailsViewModel.getProductDetails()

        binding.cartButton.setOnClickListener {
            val x = Intent(this, CartActivity::class.java)
            startActivity(x)
        }

        binding.backButton.setOnClickListener {
            val x = Intent(this, MainActivity::class.java)
            startActivity(x)
        }
    }

}
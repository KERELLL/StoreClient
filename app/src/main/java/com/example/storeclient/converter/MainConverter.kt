package com.example.storeclient.converter

import android.graphics.BitmapFactory
import com.example.storeclient.data.mainData.MainDataResponse
import com.example.storeclient.model.BestSellerItem
import com.example.storeclient.model.HotSalesItem
import com.example.storeclient.model.MainData
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainConverter {
      fun convert(response : MainDataResponse): MainData {
        return MainData(
            bestSellerItems = getListOfBestSeller(response),
            hotSalesItems = getListOfHotSale(response)
        )
    }

    private fun getListOfBestSeller(response : MainDataResponse) : MutableList<BestSellerItem>{
        val listOfBestSellerItems = mutableListOf<BestSellerItem>()
        for(i in response[0].best_seller.indices){
            listOfBestSellerItems.add(
                BestSellerItem(
                    bestSellerTitle = response[0].best_seller[i].title,
                    bestSellerBackground = response[0].best_seller[i].picture,
                    discountPrice = response[0].best_seller[i].discount_price,
                    priceWithoutDiscount = response[0].best_seller[i].price_without_discount
            ))
        }
        return listOfBestSellerItems
    }

    private fun getListOfHotSale(response: MainDataResponse) : MutableList<HotSalesItem>{
        val listOfHostSaleItems = mutableListOf<HotSalesItem>()
        for(i in response[0].home_store.indices){
            listOfHostSaleItems.add(
                HotSalesItem(
                    hotSalesTitle = response[0].home_store[i].title,
                    hotSalesSubtitle = response[0].home_store[i].subtitle,
                    hotSalesBackground = response[0].home_store[i].picture,
                    hotSalesIsNewBackground =  response[0].home_store[i].is_new
                )
            )
        }
        return listOfHostSaleItems
    }

}
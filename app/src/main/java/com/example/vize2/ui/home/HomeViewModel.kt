package com.example.vize2.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vize2.models.DummyProduct
import com.example.vize2.models.Product
import com.example.vize2.retrofit.services.IProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    var productService : IProductService? = null
    var isFailed = MutableLiveData<Boolean>()
    var products = MutableLiveData<List<Product>>()

    fun getProducts(){
        productService?.products()?.enqueue(object : Callback<DummyProduct?>{
            override fun onResponse(call: Call<DummyProduct?>, response: Response<DummyProduct?>) {
                if(response.isSuccessful){
                    val response = response.body()
                    if(response != null){
                        products.value = response.products
                        isFailed.value = false
                    }
                }else{
                    isFailed.value = true
                }
            }

            override fun onFailure(call: Call<DummyProduct?>, t: Throwable) {
                isFailed.value = true
            }

        })
    }

}
package com.example.vize2.ui.productDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vize2.models.Basket
import com.example.vize2.models.AddBasket
import com.example.vize2.models.AddBasketProduct
import com.example.vize2.models.Product
import com.example.vize2.retrofit.services.ICartService
import com.example.vize2.retrofit.services.IProductService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel : ViewModel() {

    var product = MutableLiveData<Product?>()
    var productService : IProductService? = null
    var cartService : ICartService? = null
    var isFailed = MutableLiveData<Boolean>()
    var isAddFailed = MutableLiveData<Boolean>()

    fun getProduct(id : Int){
        productService?.getProductById(id)?.enqueue(object : Callback<Product?>{
            override fun onResponse(call: Call<Product?>, response: Response<Product?>) {
                if(response.isSuccessful){
                    val response = response.body()
                    if(response != null){
                        product.value = response
                        isFailed.value = false
                    }
                }else{
                    isFailed.value = true
                }
            }

            override fun onFailure(call: Call<Product?>, t: Throwable) {
                isFailed.value = true
            }

        })
    }

    fun addToCart(addBasketProducts : List<AddBasketProduct>){
        val addBasket = AddBasket(1,addBasketProducts)
        cartService?.addToCart(addBasket)?.enqueue(object : Callback<Basket>{
            override fun onResponse(call: Call<Basket>, response: Response<Basket>) {
                if(response.isSuccessful){
                    val response = response.body()
                    Log.e("response",response.toString())
                    if(response != null){
                        isAddFailed.value = false
                    }
                }else{
                    isAddFailed.value = true
                }
            }

            override fun onFailure(call: Call<Basket>, t: Throwable) {
                isAddFailed.value = true
            }

        })
    }
}
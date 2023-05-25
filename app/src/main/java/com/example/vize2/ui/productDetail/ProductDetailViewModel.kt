package com.example.vize2.ui.productDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vize2.models.Cart
import com.example.vize2.models.CartPost
import com.example.vize2.models.CartPostProduct
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

    fun addToCart(cartPostProducts : List<CartPostProduct>){
        val cartPost = CartPost(1,cartPostProducts)
        cartService?.addToCart(cartPost)?.enqueue(object : Callback<Cart>{
            override fun onResponse(call: Call<Cart>, response: Response<Cart>) {
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

            override fun onFailure(call: Call<Cart>, t: Throwable) {
                isAddFailed.value = true
            }

        })
    }
}
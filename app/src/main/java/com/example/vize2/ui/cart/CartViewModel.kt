package com.example.vize2.ui.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vize2.models.Cart
import com.example.vize2.retrofit.services.ICartService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartViewModel : ViewModel() {

    var cart = MutableLiveData<Cart?>()
    var cartService : ICartService? = null
    var isFailed = MutableLiveData<Boolean>()

    fun getCartById(cartId : Int){
        cartService?.getCartById(cartId)?.enqueue(object : Callback<Cart?>{
            override fun onResponse(call: Call<Cart?>, response: Response<Cart?>) {
                if(response.isSuccessful){
                    val response = response.body()
                    if(response != null){
                        cart.value = response
                        isFailed.value = false
                    }
                }else{
                    isFailed.value = true
                }
            }

            override fun onFailure(call: Call<Cart?>, t: Throwable) {
                isFailed.value = true
            }

        })
    }

}
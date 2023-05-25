package com.example.vize2.retrofit.services

import com.example.vize2.models.Cart
import com.example.vize2.models.CartPost
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ICartService {

    @POST("carts/add")
    fun addToCart(@Body cartPost : CartPost) : Call<Cart>

    @GET("carts/{id}")
    fun getCartById(@Path("id") id : Int) : Call<Cart?>
}
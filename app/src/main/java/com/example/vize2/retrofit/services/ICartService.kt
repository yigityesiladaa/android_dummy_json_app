package com.example.vize2.retrofit.services

import com.example.vize2.models.Basket
import com.example.vize2.models.AddBasket
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ICartService {

    @POST("carts/add")
    fun addToCart(@Body addBasket : AddBasket) : Call<Basket>

    @GET("carts/{id}")
    fun getCartById(@Path("id") id : Int) : Call<Basket?>
}
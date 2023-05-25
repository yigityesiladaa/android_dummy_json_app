package com.example.vize2.retrofit.services

import com.example.vize2.models.DummyProduct
import com.example.vize2.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IProductService {

    @GET("products")
    fun products() : Call<DummyProduct?>

    @GET("products/{id}")
    fun getProductById(@Path("id") id : Int) : Call<Product?>


}
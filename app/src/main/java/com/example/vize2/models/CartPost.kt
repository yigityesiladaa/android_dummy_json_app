package com.example.vize2.models

data class CartPost (
    val userID: Int,
    val products: List<CartPostProduct>
)

data class CartPostProduct (
    val id: Int,
    val quantity: Int
)

package com.example.vize2.models

data class AddBasket (
    val userID: Int,
    val products: List<AddBasketProduct>
)

data class AddBasketProduct (
    val id: Int,
    val quantity: Int
)

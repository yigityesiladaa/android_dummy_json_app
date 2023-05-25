package com.example.vize2.models

data class Basket (
    val id: Int,
    val products: List<BasketResultProduct>,
    val total: Long,
    val discountedTotal: Long,
    val userId: Long,
    val totalProducts: Long,
    val totalQuantity: Long
)

data class BasketResultProduct (
    val id: Int,
    val title: String,
    val price: Long,
    val quantity: Long,
    val total: Long,
    val discountPercentage: Double,
    val discountedPrice: Long
)

package com.example.vize2.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.vize2.R
import com.example.vize2.models.BasketResultProduct

class CustomCartProductsAdapter(private val context : Context)
    : ArrayAdapter<BasketResultProduct>(context, R.layout.cart_product_list_item) {

    private var basketResultProductList = listOf<BasketResultProduct>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = LayoutInflater.from(context).inflate(R.layout.cart_product_list_item,null,true)

        val productTitle = rootView.findViewById<TextView>(R.id.txtCartProductTitle)
        val productPrice = rootView.findViewById<TextView>(R.id.txtCartProductPrice)
        val productQuantity = rootView.findViewById<TextView>(R.id.txtCartProductQuantity)

        val cartProduct = basketResultProductList[position]

        productTitle.text = cartProduct.title
        productPrice.text = "$${cartProduct.price}"
        productQuantity.text = "quantity: ${cartProduct.quantity}"

        return rootView
    }

    override fun getCount(): Int {
        return basketResultProductList.count()
    }

     fun submitList(list : List<BasketResultProduct>){
        basketResultProductList = list
        notifyDataSetChanged()
    }

}
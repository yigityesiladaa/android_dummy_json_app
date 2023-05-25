package com.example.vize2.ui.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.vize2.R
import com.example.vize2.models.CartProduct

class CustomCartProductsAdapter(private val context : Context)
    : ArrayAdapter<CartProduct>(context, R.layout.cart_product_list_item) {

    private var cartProductList = listOf<CartProduct>()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rootView = LayoutInflater.from(context).inflate(R.layout.cart_product_list_item,null,true)

        val productTitle = rootView.findViewById<TextView>(R.id.txtCartProductTitle)
        val productPrice = rootView.findViewById<TextView>(R.id.txtCartProductPrice)
        val productQuantity = rootView.findViewById<TextView>(R.id.txtCartProductQuantity)

        val cartProduct = cartProductList[position]

        productTitle.text = cartProduct.title
        productPrice.text = "$${cartProduct.price}"
        productQuantity.text = "quantity: ${cartProduct.quantity}"

        return rootView
    }

    override fun getCount(): Int {
        return cartProductList.count()
    }

     fun submitList(list : List<CartProduct>){
        cartProductList = list
        notifyDataSetChanged()
    }

}
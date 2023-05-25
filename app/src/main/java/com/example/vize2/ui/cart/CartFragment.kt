package com.example.vize2.ui.cart

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.vize2.retrofit.configs.ApiClient
import com.example.vize2.databinding.FragmentCartBinding
import com.example.vize2.models.BasketResultProduct
import com.example.vize2.retrofit.services.ICartService

class CartFragment : Fragment() {

    private  var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private var basketResultProducts = listOf<BasketResultProduct>()
    private lateinit var cartViewModel: CartViewModel
    private lateinit var customCartProductsAdapter : CustomCartProductsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        registerEvents()
        listenEvents()
    }

    private fun init(){

        customCartProductsAdapter = CustomCartProductsAdapter(requireContext())
        cartViewModel = ViewModelProvider(this)[CartViewModel::class.java]
        cartViewModel.cartService = ApiClient.getClient().create(ICartService::class.java)
        cartViewModel.getCartById(1)
    }

    private fun registerEvents(){
        binding.cartProductsListView.adapter = customCartProductsAdapter
    }

    private fun listenEvents(){
        cartViewModel.basket.observe(viewLifecycleOwner){ result->
            result?.let {
                basketResultProducts = it.products
                customCartProductsAdapter.submitList(it.products)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
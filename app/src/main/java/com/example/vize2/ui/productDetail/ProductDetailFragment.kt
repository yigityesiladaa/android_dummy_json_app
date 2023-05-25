package com.example.vize2.ui.productDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.vize2.R
import com.example.vize2.common.extensions.showToast
import com.example.vize2.retrofit.configs.ApiClient
import com.example.vize2.databinding.FragmentProductDetailBinding
import com.example.vize2.models.AddBasketProduct
import com.example.vize2.models.Product
import com.example.vize2.retrofit.services.ICartService
import com.example.vize2.retrofit.services.IProductService

class ProductDetailFragment : Fragment() {

    private  var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var productDetailViewModel: ProductDetailViewModel
    private var productId : Int? = null
    private var product : Product? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            productId = bundle.getInt("productId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductDetailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
        registerEvents()
        listenEvents()
    }

    private fun init(view : View){
        navController = Navigation.findNavController(view)
        productDetailViewModel = ViewModelProvider(this)[ProductDetailViewModel::class.java]
        productDetailViewModel.productService = ApiClient.getClient().create(IProductService::class.java)
        productDetailViewModel.cartService = ApiClient.getClient().create(ICartService::class.java)
        if(productId != null){
            productDetailViewModel.getProduct(productId!!)
        }
    }

    private fun registerEvents(){
        binding.btnOrder.setOnClickListener {
            product?.let {
                var addBasketProductList = mutableListOf<AddBasketProduct>()
                val addBasketProduct = AddBasketProduct(it.id,1)
                addBasketProductList.add(addBasketProduct)
                productDetailViewModel.addToCart(addBasketProductList)
                navController.navigate(R.id.action_productDetailFragment2_to_cartFragment)
            }
        }
    }

    private fun setProductFields(){
        product?.let { currentProduct->
            var productImages = mutableListOf<SlideModel>()
            for(image in currentProduct.images){
                productImages.add(SlideModel(image,ScaleTypes.CENTER_CROP))
            }
            binding.productImageSlider.setImageList(productImages)
            binding.txtTitle.text = "${currentProduct.brand} ${currentProduct.title}"
            binding.txtDescription.text = currentProduct.description
            binding.txtPrice.text = "$ ${currentProduct.price}"
            binding.txtRate.text = currentProduct.rating.toString()
            binding.txtStock.text = "There are currently ${currentProduct.stock} of this product"
        }
    }


    private fun listenEvents(){
        productDetailViewModel.product.observe(viewLifecycleOwner){result->
            result?.let {currentProduct->
                product = currentProduct
                setProductFields()
            }
        }

        productDetailViewModel.isAddFailed.observe(viewLifecycleOwner){result ->
            result?.let {
                if(it){
                    showToast("Order Success")
                }else{
                    showToast("Order Failed")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
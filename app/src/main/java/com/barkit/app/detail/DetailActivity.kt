package com.barkit.app.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityDetailBinding
import com.barkit.app.order.OrderActivity
import com.barkit.app.utils.loadImage
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    private val detailViewModel by viewModel<DetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val productId = intent.getStringExtra(EXTRA_PRODUCT_ID)
        productId?.let { id ->
            detailViewModel.getProductDetail(id).observe(this) {
                when (it) {
                    is Resource.Success -> {
                        populateDetailData(it.data)
                    }

                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }

            binding.btnRent.setOnClickListener {
                val orderIntent = Intent(this@DetailActivity, OrderActivity::class.java)
                orderIntent.putExtra(OrderActivity.EXTRA_PRODUCT_ID, id)
                startActivity(orderIntent)
            }
        }
    }

    private fun populateDetailData(productDetail: ProductDetail?) {
        productDetail?.let {
            with(binding) {
                imgProduct.loadImage(it.imageUrl)

                tvProductPrice.text = it.price
                tvProductName.text = it.title

                tvLessorName.text = it.lessor.storeFullName
                tvLessorCity.text = it.lessor.storeAddress

                tvStockValue.text = it.quantity
                tvCategoryValue.text = it.category
                tvSubCategoryValue.text = it.subCategory

                tvProductDesc.text = it.description
            }
        }
    }

    companion object {
        const val EXTRA_PRODUCT_ID = "extra_product_id"
    }
}
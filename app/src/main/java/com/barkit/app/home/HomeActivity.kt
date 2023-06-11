package com.barkit.app.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkit.app.adapter.CategoryAdapter
import com.barkit.app.adapter.ProductAdapter
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeViewModel.dashboardData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    populateCategories(it.data?.categories)
                    populateProducts(it.data?.products)
                }

                is Resource.Loading -> {}

                is Resource.Error -> {}
            }
        }
    }

    private fun populateCategories(categories: List<Category>?) {
        if (!categories.isNullOrEmpty()) {
            val categoryAdapter = CategoryAdapter()
            categoryAdapter.submitList(categories)

            with(binding.rvCategories) {
                val layoutManager = LinearLayoutManager(this@HomeActivity)
                layoutManager.orientation = LinearLayoutManager.HORIZONTAL

                this.layoutManager = layoutManager
                this.adapter = categoryAdapter

                setHasFixedSize(true)
            }
        }
    }

    private fun populateProducts(products: List<Product>?) {
        if (!products.isNullOrEmpty()) {
            val productAdapter = ProductAdapter()
            productAdapter.submitList(products)

            with(binding.rvProducts) {
                val layoutManager = GridLayoutManager(this@HomeActivity, 2)

                this.layoutManager = layoutManager
                this.adapter = productAdapter

                setHasFixedSize(true)
            }
        }
    }

    private companion object {
        const val TAG = "HomeActivity"
    }
}
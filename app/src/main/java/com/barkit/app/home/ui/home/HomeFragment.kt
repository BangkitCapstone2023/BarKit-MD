package com.barkit.app.home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkit.app.adapter.CategoryAdapter
import com.barkit.app.adapter.ProductAdapter
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.FragmentHomeBinding
import com.barkit.app.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), ProductAdapter.OnClickListener {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.dashboardData.observe(viewLifecycleOwner) {
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

    override fun onProductClick(product: Product) {
        val detailIntent = Intent(activity, DetailActivity::class.java)
        detailIntent.putExtra(DetailActivity.EXTRA_PRODUCT_ID, product.productId)
        startActivity(detailIntent)
    }

    private fun populateCategories(categories: List<Category>?) {
        if (!categories.isNullOrEmpty()) {
            val categoryAdapter = CategoryAdapter()
            categoryAdapter.submitList(categories)

            with(binding.rvCategories) {
                val layoutManager = LinearLayoutManager(activity)
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
            productAdapter.setOnClickListener(this)

            with(binding.rvProducts) {
                val layoutManager = GridLayoutManager(activity, 2)

                this.layoutManager = layoutManager
                this.adapter = productAdapter

                setHasFixedSize(true)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
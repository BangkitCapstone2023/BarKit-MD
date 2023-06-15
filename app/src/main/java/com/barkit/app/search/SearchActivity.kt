package com.barkit.app.search

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.barkit.app.adapter.SearchProductAdapter
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val query = intent.getStringExtra(EXTRA_QUERY)
        query?.let { q ->
            binding.toolbar.title = query

            searchViewModel.searchProduct(q).observe(this) {
                when (it) {
                    is Resource.Success -> {
                        populateListProduct(it.data)
                    }

                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        Toast.makeText(this, "Error Searching!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun populateListProduct(listProduct: List<SearchProduct>?) {
        if (listProduct != null) {
            val adapter = SearchProductAdapter()
            adapter.submitList(listProduct)

            val layoutManager = GridLayoutManager(this, 2)

            with(binding.rvProducts) {
                this.layoutManager = layoutManager
                this.adapter = adapter

                setHasFixedSize(true)
            }
        }
    }

    companion object {
        const val EXTRA_QUERY = "extra_query"
    }
}
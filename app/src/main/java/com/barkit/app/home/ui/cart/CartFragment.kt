package com.barkit.app.home.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkit.app.adapter.ProductCartAdapter
import com.barkit.app.core.domain.model.Product
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.FragmentCartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null

    private val binding get() = _binding!!
    private val cartViewModel by viewModel<CartViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartViewModel.getListCartProduct().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    populateCart(it.data)
                }

                is Resource.Loading -> {
                    Toast.makeText(
                        requireContext(),
                        "Loading cart...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is Resource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Error loading cart! ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        cartViewModel.getListCartProduct()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateCart(listProduct: List<Product>?) {
        if (!listProduct.isNullOrEmpty()) {
            val adapter = ProductCartAdapter()
            adapter.submitList(listProduct)

            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL

            with(binding.rvCart) {
                this.adapter = adapter
                this.layoutManager = layoutManager

                setHasFixedSize(true)
            }
        }
    }
}
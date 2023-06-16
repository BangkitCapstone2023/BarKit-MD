package com.barkit.app.home.ui.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.barkit.app.adapter.OrderAdapter
import com.barkit.app.core.domain.model.Order
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.FragmentUserOrderBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserOrderFragment : Fragment() {
    private var _binding: FragmentUserOrderBinding? = null

    private val binding get() = _binding!!
    private val userOrderViewModel by viewModel<UserOrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserOrderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userOrderViewModel.listOrder.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    populateListOrder(it.data)
                }

                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun populateListOrder(listOrder: List<Order>?) {
        if (listOrder != null) {
            val adapter = OrderAdapter()
            adapter.submitList(listOrder)

            with(binding.rvOrders) {
                val layoutManager = LinearLayoutManager(requireContext())
                layoutManager.orientation = LinearLayoutManager.VERTICAL

                this.layoutManager = layoutManager
                this.adapter = adapter

                setHasFixedSize(true)
            }
        }
    }
}
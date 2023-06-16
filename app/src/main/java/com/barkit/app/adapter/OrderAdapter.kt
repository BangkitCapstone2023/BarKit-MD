package com.barkit.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barkit.app.core.domain.model.Order
import com.barkit.app.databinding.ItemOrderBinding
import com.barkit.app.utils.Helper
import com.barkit.app.utils.loadImage

class OrderAdapter : ListAdapter<Order, OrderAdapter.ViewHolder>(OrderDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(order: Order) {
            with(binding) {
                val rentalDuration = Helper.getDateRange(order.startRentDate, order.endRentDate)
                val qty = order.quantityOrder
                val price = order.product.price.replace(".", "").toInt()
                val totalPrice = price * qty * rentalDuration

                tvStartDate.text = order.startRentDate
                tvOrderStatus.text = order.status
                tvProductName.text = order.product.title
                tvRentInformation.text = "$qty Barang, $rentalDuration Hari Sewa"
                tvTotalPrice.text = "Rp.$totalPrice"

                imgProduct.loadImage(order.product.imageUrl)
            }
        }
    }

    private class OrderDiffCallback : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.orderId == newItem.orderId
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.orderId == newItem.orderId && oldItem.productId == newItem.productId
        }

    }
}
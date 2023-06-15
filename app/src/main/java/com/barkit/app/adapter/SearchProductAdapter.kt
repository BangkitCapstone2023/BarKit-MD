package com.barkit.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barkit.app.core.domain.model.SearchProduct
import com.barkit.app.databinding.ItemProductBinding
import com.barkit.app.utils.loadImage

class SearchProductAdapter :
    ListAdapter<SearchProduct, SearchProductAdapter.ViewHolder>(SearchProductDiffCallback()) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchProductAdapter.ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchProductAdapter.ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(searchProduct: SearchProduct) {
            with(binding) {
                tvProductName.text = searchProduct.title
                tvProductPrice.text = "Rp.${searchProduct.price}"

                imgProduct.loadImage(searchProduct.imageUrl)
            }
        }
    }

    private class SearchProductDiffCallback : DiffUtil.ItemCallback<SearchProduct>() {
        override fun areItemsTheSame(oldItem: SearchProduct, newItem: SearchProduct): Boolean {
            return oldItem.productId == newItem.productId
        }

        override fun areContentsTheSame(oldItem: SearchProduct, newItem: SearchProduct): Boolean {
            return oldItem.productId == newItem.productId
        }
    }
}
package com.barkit.app.store.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.barkit.app.R
import com.barkit.app.core.domain.model.Store
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityStoreHomeBinding
import com.barkit.app.store.StoreViewModel
import com.barkit.app.store.product.AddProductActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class StoreHomeActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityStoreHomeBinding

    private val storeViewModel by viewModel<StoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStoreHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeViewModel.storeProfile.observe(this) {
            when (it) {
                is Resource.Success -> {
                    populateStoreData(it.data)
                }

                is Resource.Loading -> {}
                is Resource.Error -> {}
            }
        }

        binding.btnAddProduct.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_product -> {
                val addProductIntent = Intent(this, AddProductActivity::class.java)
                startActivity(addProductIntent)
            }
        }
    }

    private fun populateStoreData(store: Store?) {
        if (store != null) {
            with(binding) {
                tvStoreName.text = store.storeFullName
                tvStoreEmail.text = store.storeEmail
            }
        }
    }
}
package com.barkit.app.addstore

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.barkit.app.R
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityAddStoreBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddStoreActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddStoreBinding

    private val addStoreViewModel by viewModel<AddStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddStoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddStore.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)

        setLoading(false)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_add_store -> {
                with(binding) {
                    val name = edtStoreName.text.toString().trim()
                    val address = edtStoreAddress.text.toString().trim()
                    val email = edtStoreEmail.text.toString().trim()
                    val phone = edtStorePhone.text.toString().trim()

                    addStoreViewModel.addStore(name, address, email, phone)
                        .observe(this@AddStoreActivity) {
                            when (it) {
                                is Resource.Success -> {
                                    setLoading(false)
                                    Toast.makeText(
                                        this@AddStoreActivity,
                                        "Success add store",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }

                                is Resource.Loading -> {
                                    setLoading(true)
                                }

                                is Resource.Error -> {
                                    Toast.makeText(
                                        this@AddStoreActivity,
                                        "Error add store: ${it.message}",
                                        Toast.LENGTH_SHORT
                                    )
                                        .show()
                                }
                            }
                        }
                }
            }

            R.id.btn_cancel -> finish()
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }
}
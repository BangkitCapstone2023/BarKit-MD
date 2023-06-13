package com.barkit.app.order

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cn.pedant.SweetAlert.SweetAlertDialog
import com.barkit.app.R
import com.barkit.app.core.domain.model.ProductDetail
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityOrderBinding
import com.barkit.app.utils.loadImage
import com.google.android.material.datepicker.MaterialDatePicker
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

class OrderActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityOrderBinding

    private val orderViewModel by viewModel<OrderViewModel>()

    private var productId = ""
    private var quantity = 1
    private var productPrice = 0
    private var rentalDuration = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getStringExtra(EXTRA_PRODUCT_ID)
        id?.let { pId ->
            orderViewModel.getProductDetail(pId).observe(this) {
                when (it) {
                    is Resource.Success -> {
                        productId = pId
                        populateDetailData(it.data)
                    }

                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }
        }

        with(binding) {
            ArrayAdapter.createFromResource(
                this@OrderActivity,
                R.array.payments,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerPaymentMethod.adapter = it
            }

            ArrayAdapter.createFromResource(
                this@OrderActivity,
                R.array.couriers,
                android.R.layout.simple_spinner_item
            ).also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCourier.adapter = it
            }

            tvQuantity.text = quantity.toString()
            tvStartDate.text = "-"
            tvEndDate.text = "-"

            btnSubtractQty.setOnClickListener(this@OrderActivity)
            btnAddQty.setOnClickListener(this@OrderActivity)
            btnSetDuration.setOnClickListener(this@OrderActivity)
            btnConfirm.setOnClickListener(this@OrderActivity)
        }

        setLoading(false)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_subtract_qty -> {
                quantity--
                binding.tvQuantity.text = quantity.toString()

                setQuantityButtonState()
                setTotalPrice()
            }

            R.id.btn_add_qty -> {
                quantity++
                binding.tvQuantity.text = quantity.toString()

                setQuantityButtonState()
                setTotalPrice()
            }

            R.id.btn_set_duration -> showDatePicker()

            R.id.btn_confirm -> confirm()
        }
    }

    private fun populateDetailData(productDetail: ProductDetail?) {
        productDetail?.let {
            with(binding) {
                imgProduct.loadImage(it.imageUrl)
                tvProductName.text = it.title
                tvLessorName.text = it.lessor.storeFullName
                tvProductPrice.text = it.price

                productPrice = it.price.replace(".", "").toInt()

                setQuantityButtonState()
                setTotalPrice()
            }
        }
    }

    private fun showDatePicker() {
        val picker = MaterialDatePicker.Builder.dateRangePicker()
            .setTitleText("Set Rental Duration")
            .build()

        picker.addOnPositiveButtonClickListener {
            with(binding) {
                val diff = it.second - it.first
                rentalDuration = TimeUnit.MILLISECONDS.toDays(diff).toInt()

                tvStartDate.text = parseToDateString(it.first)
                tvEndDate.text = parseToDateString(it.second)

                setTotalPrice()
            }
        }

        picker.addOnNegativeButtonClickListener {
            picker.dismiss()
        }

        picker.show(supportFragmentManager, null)
    }

    private fun parseToDateString(time: Long): String {
        val date = Date(time)
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

        return formatter.format(date)
    }

    private fun setTotalPrice() {
        val totalPrice = productPrice * quantity * rentalDuration
        val totalPriceText = "Rp.$totalPrice/hari"

        binding.tvTotalPrice.text = totalPriceText
    }

    private fun setQuantityButtonState() {
        binding.btnSubtractQty.isEnabled = quantity > 1
        binding.btnAddQty.isEnabled = quantity < 10
    }

    private fun confirm() {
        val deliveryAddress = binding.tvDeliveryAddress.text.toString().trim()
        val startRentDate = binding.tvStartDate.text.toString()
        val endRentDate = binding.tvEndDate.text.toString()
        val paymentUse = binding.spinnerPaymentMethod.selectedItem.toString()
        val courier = binding.spinnerCourier.selectedItem.toString()

        orderViewModel.orderProduct(
            productId,
            deliveryAddress,
            startRentDate,
            endRentDate,
            quantity,
            paymentUse,
            courier
        ).observe(this) {
            when (it) {
                is Resource.Success -> {
                    setLoading(false)
                    SweetAlertDialog(this@OrderActivity, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("Order Success!")
                        .setContentText("You're order is successful!")
                        .setConfirmButton("See my orders") {
                            finish()
                        }
                        .setConfirmButtonBackgroundColor(R.color.red)
                        .setCancelButton("Back") {
                            finish()
                        }
                        .setCancelButtonBackgroundColor(R.color.gray_400)
                        .show()
                }

                is Resource.Loading -> {
                    setLoading(true)
                    Log.d(TAG, "ORDER LOADING!")
                }

                is Resource.Error -> {
                    setLoading(false)
                    Log.d(TAG, "ORDER ERROR!")
                    SweetAlertDialog(this@OrderActivity, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Order Error!")
                        .setContentText(it.message ?: "Order error without message!")
                        .setConfirmButton("OK") {
                            finish()
                        }
                        .setConfirmButtonBackgroundColor(R.color.red)
                        .show()
                }
            }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    companion object {
        private const val TAG = "OrderActivity"
        const val EXTRA_PRODUCT_ID = "extra_product_id"
    }
}
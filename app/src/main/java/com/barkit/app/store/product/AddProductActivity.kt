package com.barkit.app.store.product

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import cn.pedant.SweetAlert.SweetAlertDialog
import com.barkit.app.R
import com.barkit.app.core.domain.model.Category
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityAddProductBinding
import com.barkit.app.store.StoreViewModel
import com.barkit.app.utils.Helper.bitmapToFile
import com.barkit.app.utils.Helper.reduceFileImage
import com.barkit.app.utils.Helper.uriToFile
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class AddProductActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityAddProductBinding

    private var getFile: File? = null

    private val launcherPickImage = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            val pickedImageUri: Uri? = data?.data

            if (pickedImageUri != null) {
                val myFile = uriToFile(pickedImageUri, this@AddProductActivity)
                getFile = myFile

                binding.btnImgProduct.setImageURI(pickedImageUri)
                binding.tvAddPhoto.visibility = View.GONE
            } else {
                val imageBitmap = result.data?.extras?.get("data") as Bitmap
                getFile = bitmapToFile(imageBitmap, this@AddProductActivity)

                binding.btnImgProduct.setImageBitmap(imageBitmap)
                binding.tvAddPhoto.visibility = View.GONE
            }
        }
    }

    private val storeViewModel by viewModel<StoreViewModel>()
    private val categoryNames = ArrayList<String>()
    private val subcategories = ArrayList<ArrayList<String>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        storeViewModel.categories.observe(this) {
            when (it) {
                is Resource.Success -> {
                    setLoading(false)
                    it.data?.let { listCategory ->
                        initCategoryOption(listCategory)
                    }
                }

                is Resource.Loading -> setLoading(true)
                is Resource.Error -> {
                    setLoading(true)
                    Toast.makeText(
                        this,
                        "Error loading category: ${it.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        binding.btnImgProduct.setOnClickListener(this)
        binding.btnAddProduct.setOnClickListener(this)
        binding.btnCancel.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_img_product -> {
                val captureImageIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val pickImageIntent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

                val chooserIntent = Intent.createChooser(captureImageIntent, "Select Image")
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(pickImageIntent))

                launcherPickImage.launch(chooserIntent)
            }

            R.id.btn_add_product -> addProduct()

            R.id.btn_cancel -> finish()
        }
    }

    private fun initCategoryOption(listCategory: List<Category>) {
        categoryNames.add(CATEGORY_HINT)

        listCategory.forEach { category ->
            val subcategory = ArrayList<String>()
            subcategory.add(SUB_CATEGORY_HINT)
            subcategory.addAll(category.subcategories.map { it.name })

            categoryNames.add(category.name)
            subcategories.add(subcategory)
        }

        val categoryAdapter = ArrayAdapter(
            this@AddProductActivity,
            android.R.layout.simple_spinner_item,
            categoryNames
        )
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCategory.adapter = categoryAdapter

        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position > 0) setSubCategory(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }

    private fun setSubCategory(index: Int) {
        val subcategoryAdapter = ArrayAdapter(
            this@AddProductActivity,
            android.R.layout.simple_spinner_item,
            subcategories[index - 1]
        )
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerSubCategory.adapter = subcategoryAdapter
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private fun addProduct() {
        if (getFile == null) return

        with(binding) {
            val file = reduceFileImage(getFile as File)
            val title = edtProductTitle.text.toString().trim()
            val description = edtProductDesc.text.toString().trim()
            val price = edtProductPrice.text.toString().trim().toInt()
            val category = spinnerCategory.selectedItem.toString()
            val subCategory = spinnerSubCategory.selectedItem.toString()
            val quantity = edtProductQty.text.toString().trim().toInt()

            storeViewModel.addProduct(
                file,
                title,
                description,
                price,
                category,
                subCategory,
                quantity
            ).observe(this@AddProductActivity) {
                when (it) {
                    is Resource.Success -> {
                        setLoading(false)
                        SweetAlertDialog(this@AddProductActivity, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText("Success!")
                            .setContentText("Adding product is successful!")
                            .setConfirmButton("OK") {
                                finish()
                            }
                            .show()
                    }

                    is Resource.Loading -> {
                        setLoading(true)
                        Toast.makeText(
                            this@AddProductActivity,
                            "Uploading product...",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Error -> {
                        setLoading(false)
                        SweetAlertDialog(this@AddProductActivity, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error!")
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
    }

    private companion object {
        const val TAG = "AddProductActivity"
        const val CATEGORY_HINT = "-- Select Category --"
        const val SUB_CATEGORY_HINT = "-- Select Sub-Category --"
    }
}
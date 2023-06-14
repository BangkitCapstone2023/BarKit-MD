package com.barkit.app.authentication

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.barkit.app.R
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityRegisterBinding
import com.barkit.app.utils.isValidEmail
import com.barkit.app.utils.isValidPassword
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterActivity : AppCompatActivity(), View.OnClickListener,
    RadioGroup.OnCheckedChangeListener {
    private lateinit var binding: ActivityRegisterBinding

    private val authViewModel by viewModel<AuthViewModel>()
    private var gender = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rgGender.setOnCheckedChangeListener(this)
        binding.btnRegister.setOnClickListener(this)

        setLoading(false)
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_register) {
            with(binding) {
                val fullName = edtFullName.text.toString().trim()
                val username = edtUsername.text.toString().trim()
                val email = edtEmail.text.toString().trim()
                val password = edtPassword.text.toString().trim()
                val address = edtAddress.text.toString().trim()
                val phone = edtPhone.text.toString().trim()

                var isValid = true

                if (fullName.isEmpty()) {
                    edtFullName.error = "Please enter your full name!"
                    isValid = false
                }

                if (username.isEmpty()) {
                    edtUsername.error = "Please enter your username!"
                    isValid = false
                }

                if (!email.isValidEmail()) {
                    edtEmail.error = "Please enter a valid email!"
                    isValid = false
                }

                if (!password.isValidPassword()) {
                    edtPassword.error = "Password can't be empty and min. 6 characters"
                    isValid = false
                }

                if (address.isEmpty()) {
                    edtAddress.error = "Please enter your address!"
                    isValid = false
                }

                if (phone.isEmpty()) {
                    edtPhone.error = "Please enter your phone number!"
                    isValid = false
                }

                if (gender.isEmpty()) {
                    Toast.makeText(
                        this@RegisterActivity,
                        "Please choose gender!",
                        Toast.LENGTH_SHORT
                    ).show()
                    isValid = false
                }

                if (isValid) {
                    authViewModel.register(
                        fullName,
                        username,
                        email,
                        password,
                        address,
                        phone,
                        gender
                    ).observe(this@RegisterActivity) {
                        when (it) {
                            is Resource.Success -> {
                                Log.d(TAG, "Register Success!")
                                setLoading(false)
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Register Sukses! Silahkan login!",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()

                                finish()
                            }

                            is Resource.Loading -> {
                                Log.d(TAG, "Register Process...")
                                setLoading(true)
                            }

                            is Resource.Error -> {
                                Log.e(TAG, "Register Error: ${it.message}")
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Register Error: ${it.message}",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
        when (checkedId) {
            R.id.rb_male -> gender = "Male"
            R.id.rb_female -> gender = "Female"
        }
    }

    private fun setLoading(isLoading: Boolean) {
        binding.progressBar.isVisible = isLoading
    }

    private companion object {
        const val TAG = "RegisterActivity"
    }
}
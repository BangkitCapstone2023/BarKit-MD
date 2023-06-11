package com.barkit.app.authentication

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.barkit.app.R
import com.barkit.app.core.utils.Resource
import com.barkit.app.databinding.ActivityLoginBinding
import com.barkit.app.home.HomeActivity
import com.barkit.app.utils.isValidEmail
import com.barkit.app.utils.isValidPassword
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityLoginBinding

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            btnLogin.setOnClickListener(this@LoginActivity)
            btnRegister.setOnClickListener(this@LoginActivity)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                with(binding) {
                    val email = edtEmail.text.toString().trim()
                    val password = edtPassword.text.toString().trim()

                    var isValid = true

                    if (!email.isValidEmail()) {
                        edtEmail.error = "Please enter a valid email!"
                        isValid = false
                    }

                    if (!password.isValidPassword()) {
                        edtPassword.error = "Minimum password length is 6"
                        isValid = false
                    }

                    if (isValid) {
                        authViewModel.login(email, password).observe(this@LoginActivity) {
                            when (it) {
                                is Resource.Success -> {
                                    Log.d(TAG, "Login Success: ${it.data?.renter?.fullName}")
                                    val homeIntent =
                                        Intent(this@LoginActivity, HomeActivity::class.java)
                                    startActivity(homeIntent)
                                }

                                is Resource.Loading -> {
                                    Log.d(TAG, "Login Process...")
                                }

                                is Resource.Error -> {
                                    Log.e(TAG, "Login Error: ${it.message}")
                                }
                            }
                        }
                    }
                }
            }

            R.id.btn_register -> {
                val registerIntent = Intent(this, RegisterActivity::class.java)
                startActivity(registerIntent)
            }
        }
    }

    private companion object {
        const val TAG = "LoginActivity"
    }
}
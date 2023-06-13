package com.barkit.app.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.barkit.app.R
import com.barkit.app.authentication.LoginActivity
import com.barkit.app.core.utils.Resource
import com.barkit.app.home.HomePageActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.isLogin.observe(this) {
            when (it) {
                is Resource.Error -> goToLogin()
                is Resource.Loading -> {}
                is Resource.Success -> goToHome()
            }
        }
    }

    private fun goToLogin() {
        lifecycleScope.launch {
            delay(2000)

            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun goToHome() {
        lifecycleScope.launch {
            delay(2000)

            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
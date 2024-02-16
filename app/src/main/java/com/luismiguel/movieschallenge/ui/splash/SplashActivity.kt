package com.luismiguel.movieschallenge.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.luismiguel.movieschallenge.core.loading.Loading
import com.luismiguel.movieschallenge.databinding.ActivitySplashBinding
import com.luismiguel.movieschallenge.ui.home.HomeActivity
import com.luismiguel.movieschallenge.ui.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val splashViewModel: SplashViewModel by viewModels()
    private lateinit var loadingSplash: Loading
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)
        loadingSplash = splashBinding.loadingSplash
        lifecycleScope.launch {
            showLoading()
            delay(2000)
            hideLoading()
            val user = splashViewModel.getUser()
            if(!user.isNullOrEmpty()) {
                val intent = Intent(this@SplashActivity, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun hideLoading() {
        loadingSplash.visibility = View.GONE
    }

    private fun showLoading() {
        loadingSplash.visibility = View.VISIBLE
    }
}
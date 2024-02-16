package com.luismiguel.movieschallenge.ui.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.luismiguel.movieschallenge.R
import com.luismiguel.movieschallenge.databinding.ActivityHomeBinding
import com.luismiguel.movieschallenge.ui.splash.SplashActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var homeBinding: ActivityHomeBinding
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navController: NavController
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        homeBinding.apply {
            toggle = ActionBarDrawerToggle(
                this@HomeActivity,
                drawerLayout,
                homeBinding.tbHome,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
            )
            drawerLayout.addDrawerListener(toggle)

            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_fragment) as NavHostFragment
            navController = navHostFragment.navController

            menuHamburger.setOnClickListener {
                drawerLayout.openDrawer(GravityCompat.START)
            }

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_one -> {
                        menuPrincipal.text = it.title
                        navHostFragment.findNavController().navigate(R.id.upcomingFragment)
                    }

                    R.id.menu_two -> {
                        menuPrincipal.text = it.title
                        navHostFragment.findNavController().navigate(R.id.searchFragment)
                    }

                    R.id.menu_three -> {
                        homeViewModel.clearPreferences()
                        val intent = Intent(this@HomeActivity, SplashActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
                drawerLayout.closeDrawer(GravityCompat.START)
                true
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val numeroFragment = homeViewModel.numeroFragments.value
                when (numeroFragment) {
                    1, 2 -> finish()
                    3 -> navController.navigateUp()
                    else -> {
                        navController.navigateUp()
                    }
                }
            }

        })
    }

    fun loadingVisible(visibility:Boolean){
        homeBinding.ldgLoading.isVisible = visibility
    }

    fun setTitle(title:String){
        homeBinding.menuPrincipal.text = title
    }

    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
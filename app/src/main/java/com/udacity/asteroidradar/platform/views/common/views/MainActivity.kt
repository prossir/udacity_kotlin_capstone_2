package com.udacity.asteroidradar.platform.views.common.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initUi()
    }

    // Toolbar can be used directly with the nav graph instead of being obligated to use the menu
    private fun initUi() {
        setSupportActionBar(binding.toolbar)
        NavigationUI.setupActionBarWithNavController(this, binding.navHostFragment.getFragment<NavHostFragment>().navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp()
    }

}
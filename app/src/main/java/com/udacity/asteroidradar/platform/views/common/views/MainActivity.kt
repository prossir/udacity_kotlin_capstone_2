package com.udacity.asteroidradar.platform.views.common.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
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
    }

}
package com.udacity.asteroidradar.platform.views.asteroid_detail.views


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetailBinding.inflate(inflater)
        initObservers()
        initUi()
        return binding.root
    }

    private fun initObservers() {

    }

    private fun initUi() {
        binding.lifecycleOwner = this

        arguments?.let {
            binding.asteroid = DetailFragmentArgs.fromBundle(it).selectedAsteroid
        }

        binding.helpButton.setOnClickListener {
            displayAstronomicalUnitExplanationDialog()
        }
    }

    private fun displayAstronomicalUnitExplanationDialog() {
        activity?.let {
            val builder = AlertDialog.Builder(it)
                .setMessage(getString(R.string.astronomica_unit_explanation))
                .setPositiveButton(android.R.string.ok, null)
            builder.create().show()
        }

    }

}
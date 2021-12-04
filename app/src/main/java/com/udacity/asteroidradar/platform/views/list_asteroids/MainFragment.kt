package com.udacity.asteroidradar.platform.views.list_asteroids

import android.os.*
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.platform.views.common.views.MainViewModel
import com.udacity.asteroidradar.platform.views.common.views.MainViewState
import com.udacity.asteroidradar.utils.extensions.negativeHaptics


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: AsteroidsAdapter
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initUi()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, { state ->
            when(state) {
                is MainViewState.SuccessInGettingAsteroids -> {
                    viewModel.asteroids.observe(viewLifecycleOwner, {
                        if(it.isEmpty()) {
                            viewModel.stateMessage.value = "No asteroids found"
                        }
                        adapter.submitList(it)
                    })
                }
                is MainViewState.Failure -> {
                    activity?.let {
                        context?.negativeHaptics()
                    }
                }
                else -> { /* No case */ }
            }

        })
    }

    private fun initUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        adapter = AsteroidsAdapter()
        binding.rvAsteroids.adapter = adapter
    }

}
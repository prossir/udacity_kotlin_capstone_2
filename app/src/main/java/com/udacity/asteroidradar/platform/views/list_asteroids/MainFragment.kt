package com.udacity.asteroidradar.platform.views.list_asteroids

import android.os.*
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel
import com.udacity.asteroidradar.platform.views.common.views.MainViewModel
import com.udacity.asteroidradar.platform.views.common.views.MainViewState
import com.udacity.asteroidradar.utils.extensions.negativeHaptics


class MainFragment : Fragment(), OnAsteroidClicked {

    private lateinit var binding: FragmentMainBinding
    private val viewModel by activityViewModels<MainViewModel>()
    private val adapter: AsteroidsAdapter = AsteroidsAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        setHasOptionsMenu(true)
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
        return when (item.itemId) {
            R.id.show_weekly_asteroids -> {
                viewModel.asteroidFilter.value = AsteroidFilterEnum.WEEKLY.type
                return true
            }
            R.id.show_today_asteroids -> {
                viewModel.asteroidFilter.value = AsteroidFilterEnum.DAILY.type
                return true
            }
            R.id.show_all_asteroids -> {
                viewModel.asteroidFilter.value = AsteroidFilterEnum.NO_FILTER.type
                return true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun initObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is MainViewState.Failure -> {
                    activity?.let {
                        context?.negativeHaptics()
                    }
                }
                else -> { /* No case */
                }
            }
        }

        viewModel.asteroids.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                viewModel.stateMessage.value = R.string.error_no_asteroids
            }
            adapter.submitList(it)
        }
    }

    private fun initUi() {
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.rvAsteroids.adapter = adapter
    }

    // I do not know if it is required per se to send it through the navigate
    // with secure arguments as I could simply put an observable on the current selected
    // asteroid and just navigate there.
    override fun onAsteroidClickedListener(asteroid: AsteroidModel) {
        requireView().findNavController().navigate(MainFragmentDirections.actionToDetail(asteroid))
    }

}
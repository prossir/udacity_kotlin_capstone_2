package com.udacity.asteroidradar.platform.views.common.views

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.udacity.asteroidradar.domain.use_case.asteroid.GetAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.GetPictureOfTheDayUseCase
import com.udacity.asteroidradar.platform.views.common.mapper.AsteroidMapper
import com.udacity.asteroidradar.platform.views.common.mapper.PictureOfTheDayMapper
import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel
import com.udacity.asteroidradar.platform.views.common.model.PictureOfTheDayModel
import com.udacity.asteroidradar.utils.extensions.safeLaunch
import com.udacity.asteroidradar.utils.extensions.with
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


class MainViewModel(
    getPictureOfTheDayUseCase: GetPictureOfTheDayUseCase,
    private val getAsteroidsUseCase: GetAsteroidsUseCase,
    private val pictureOfTheDayMapper: PictureOfTheDayMapper,
    private val asteroidMapper: AsteroidMapper,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val viewState: LiveData<MainViewState>
        get() = _viewState
    private val _viewState: MutableLiveData<MainViewState> = MutableLiveData()

    val isLoading: ObservableBoolean = ObservableBoolean(true)
    val isError: ObservableBoolean = ObservableBoolean(false)
    val stateMessage: MutableLiveData<String> = MutableLiveData("")

    // Picture of the day
    val pictureOfTheDay: LiveData<PictureOfTheDayModel?>
        get() = _pictureOfTheDay
    private val _pictureOfTheDay: LiveData<PictureOfTheDayModel?>

    init {
        isLoading.set(true)
        _pictureOfTheDay = Transformations.map(getPictureOfTheDayUseCase()) { it?.let { pictureOfTheDay -> pictureOfTheDayMapper.map(pictureOfTheDay) } }
        getAsteroids()
        isLoading.set(false)
    }

    fun retry() {
        isError.set(false)
        isLoading.set(true)
        getAsteroids()
    }

    // Asteroids
    val asteroids: LiveData<List<AsteroidModel>>
        get() = _asteroids
    private lateinit var _asteroids: LiveData<List<AsteroidModel>>

    private fun getAsteroids() {
        viewModelScope.safeLaunch(::getAsteroidsExceptionHandler) {
            _asteroids = with(dispatcher) { Transformations.map(getAsteroidsUseCase()) { asteroidMapper.map(it) } }
            _viewState.value = MainViewState.SuccessInGettingAsteroids
            isLoading.set(false)
        }
    }

    private fun getAsteroidsExceptionHandler(t: Throwable) {
        Timber.d(t)
        isLoading.set(false)
        isError.set(true)
        stateMessage.value = "There was an error getting the asteroids. Please retry."
        _viewState.value = MainViewState.Failure
    }

}
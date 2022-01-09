package com.udacity.asteroidradar.platform.views.common.views

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.udacity.asteroidradar.domain.use_case.asteroid.GetAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.asteroid.SaveAsteroidsUseCase
import com.udacity.asteroidradar.domain.use_case.picture_of_the_day.GetPictureOfTheDayUseCase
import com.udacity.asteroidradar.platform.views.common.mapper.AsteroidMapper
import com.udacity.asteroidradar.platform.views.common.mapper.PictureOfTheDayMapper
import com.udacity.asteroidradar.platform.views.common.model.AsteroidModel
import com.udacity.asteroidradar.platform.views.common.model.PictureOfTheDayModel
import com.udacity.asteroidradar.utils.extensions.safeLaunch
import com.udacity.asteroidradar.utils.extensions.withDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import timber.log.Timber


class MainViewModel(
    getPictureOfTheDayUseCase: GetPictureOfTheDayUseCase,
    getAsteroidsUseCase: GetAsteroidsUseCase,
    val saveAsteroidsUseCase: SaveAsteroidsUseCase,
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

    val asteroids: LiveData<List<AsteroidModel>>
        get() = _asteroids
    private val _asteroids: LiveData<List<AsteroidModel>>

    init {
        isLoading.set(true)
        _pictureOfTheDay = Transformations.map(getPictureOfTheDayUseCase()) { it?.let { pictureOfTheDay -> pictureOfTheDayMapper.map(pictureOfTheDay) } }
        _asteroids = Transformations.map(getAsteroidsUseCase()) { asteroidMapper.map(it) }
        isLoading.set(false)
    }

    fun retry() {
        isError.set(false)
        isLoading.set(true)

        viewModelScope.safeLaunch(::saveAsteroidsExceptionHandler) {
            if(withDispatcher(dispatcher) { saveAsteroidsUseCase() }) {
                isLoading.set(false)
            } else {
                throw Exception("Asteroids were not load from the backend.")
            }
        }
    }

    private fun saveAsteroidsExceptionHandler(t: Throwable) {
        Timber.d(t)
        isLoading.set(false)
        isError.set(true)
        stateMessage.value = "There was an error getting the asteroids. Please retry."
        _viewState.value = MainViewState.Failure
    }

}
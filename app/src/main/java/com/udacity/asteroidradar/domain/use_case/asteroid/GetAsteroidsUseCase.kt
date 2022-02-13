package com.udacity.asteroidradar.domain.use_case.asteroid

import androidx.lifecycle.LiveData
import com.udacity.asteroidradar.domain.entity.Asteroid
import com.udacity.asteroidradar.domain.repository.AsteroidRepository


class GetAsteroidsUseCase(private val asteroidRepository: AsteroidRepository) {

    operator fun invoke(filter: LiveData<Int?>) : LiveData<List<Asteroid>> {
        return asteroidRepository.getAsteroidsFilteredBy(filter)
    }

}
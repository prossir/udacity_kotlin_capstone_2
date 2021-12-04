package com.udacity.asteroidradar.domain.use_case.asteroid

import com.udacity.asteroidradar.domain.repository.AsteroidRepository


class SaveAsteroidsUseCase(private val asteroidRepository: AsteroidRepository) {

    suspend operator fun invoke() : Boolean {
        return asteroidRepository.saveAsteroids()
    }

}
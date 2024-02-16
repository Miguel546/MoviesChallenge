package com.luismiguel.movieschallenge.domain.usecase.local

import com.luismiguel.movieschallenge.domain.repository.ILocalRepository
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val iLocalRepository: ILocalRepository) {
    suspend operator fun invoke() = iLocalRepository.getAllMoviesDB()

}

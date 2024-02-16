package com.luismiguel.movieschallenge.domain.usecase.local

import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.repository.ILocalRepository
import javax.inject.Inject

class AddMovieUseCase @Inject constructor(private val iLocalRepository: ILocalRepository) {
    suspend operator fun invoke(movie: MovieModel) = iLocalRepository.addMovieDB(movie)

}
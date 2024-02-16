package com.luismiguel.movieschallenge.domain.usecase.remote

import com.luismiguel.movieschallenge.domain.repository.IRemoteRepository
import javax.inject.Inject

class GetNowMoviesUseCase @Inject constructor(private val iRemoteRepository: IRemoteRepository) {
    suspend fun getNowMovies(page: Int) = iRemoteRepository.getNowPlayingMovies(page)
}
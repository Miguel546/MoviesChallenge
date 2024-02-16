package com.luismiguel.movieschallenge.domain.repository

import com.luismiguel.movieschallenge.domain.model.MovieModel
import kotlinx.coroutines.flow.Flow

interface ILocalRepository {
    suspend fun addMovieDB(movie: MovieModel)

    suspend fun getAllMoviesDB(): Flow<List<MovieModel>>

    suspend fun getMovieDB(id: Int): Flow<List<MovieModel>>
}
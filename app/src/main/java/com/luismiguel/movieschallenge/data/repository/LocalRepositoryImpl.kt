package com.luismiguel.movieschallenge.data.repository

import com.luismiguel.movieschallenge.data.dao.MovieFlowDao
import com.luismiguel.movieschallenge.data.entities.toDatabase
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.model.toDomain
import com.luismiguel.movieschallenge.domain.repository.ILocalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val movieDao: MovieFlowDao) :
    ILocalRepository {

    override suspend fun addMovieDB(movie: MovieModel) {
        movieDao.add(movie.toDatabase())
    }

    override suspend fun getAllMoviesDB(): Flow<List<MovieModel>> = flow {
        movieDao.getAllMoviesDB().collect {
                emit(it.map { it.toDomain() })

        }
    }.catch { it.printStackTrace() }

    override suspend fun getMovieDB(id: Int): Flow<List<MovieModel>> = flow {
        movieDao.getMovie(id).collect {
            emit(it.map { it.toDomain() })
        }
    }
}
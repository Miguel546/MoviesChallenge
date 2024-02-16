package com.luismiguel.movieschallenge.data.repository

import com.luismiguel.movieschallenge.data.network.MovieApiService
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.data.network.response.toDomain
import com.luismiguel.movieschallenge.domain.repository.IRemoteRepository
import com.luismiguel.movieschallenge.utils.Constants.ERROR_UNEXPECTED

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteRepositoryImpl @Inject constructor(private val movieApiService: MovieApiService):
    IRemoteRepository {
    override suspend fun getNowPlayingMovies(page: Int, apiKey: String) = flow {
        emit(DataStatusRemote.loading())
        val response = movieApiService.getNowPlayingMovies(page)
        val code = response.code()
        when (code){
            in 200 .. 299 -> {
                val body = response.body()
                emit(DataStatusRemote.success(code, body?.toDomain()))
            }

            else -> {
                val errorBody = response.errorBody()?.string() ?: ERROR_UNEXPECTED
                emit(DataStatusRemote.error(code, errorBody))
            }
        }
    }.catch {
        emit(DataStatusRemote.error(-1, it.message ?: ERROR_UNEXPECTED))
    }.flowOn(Dispatchers.IO)
}
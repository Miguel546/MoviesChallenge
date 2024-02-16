package com.luismiguel.movieschallenge.domain.repository

import com.luismiguel.movieschallenge.BuildConfig.API_KEY
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import kotlinx.coroutines.flow.Flow

interface IRemoteRepository {
    suspend fun getNowPlayingMovies(page: Int, apiKey: String = API_KEY): Flow<DataStatusRemote<UpcomingModel?>>
}
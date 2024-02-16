package com.luismiguel.movieschallenge.domain.usecase

import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.data.network.response.DatesResponse
import com.luismiguel.movieschallenge.data.network.response.MovieResultResponse
import com.luismiguel.movieschallenge.data.network.response.UpcomingResponse
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import com.luismiguel.movieschallenge.domain.repository.IRemoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRemoteRepositoryAndroid: IRemoteRepository {
    override suspend fun getNowPlayingMovies(
        page: Int,
        apiKey: String
    ): Flow<DataStatusRemote<UpcomingModel?>> {
        val datesResponse = DatesResponse("2024-03-13", "2024-02-21")
        val movieResultReponse = MovieResultResponse(false, null, 1, "en", "Madame Web", "Forced to confront revelations about her past...", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val upcomingResponse = UpcomingResponse(datesResponse, 1, listOf(movieResultReponse), 1, 1)
        return flow {DataStatusRemote.success(200, upcomingResponse)}
    }
}
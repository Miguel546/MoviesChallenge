package com.luismiguel.movieschallenge.data.network

import com.luismiguel.movieschallenge.BuildConfig.API_KEY
import com.luismiguel.movieschallenge.data.network.response.UpcomingResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("3/movie/upcoming")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<UpcomingResponse>
}
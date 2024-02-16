package com.luismiguel.movieschallenge.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luismiguel.movieschallenge.BuildConfig.API_KEY
import com.luismiguel.movieschallenge.data.network.MovieApiService
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.data.network.response.DatesResponse
import com.luismiguel.movieschallenge.data.network.response.MovieResultResponse
import com.luismiguel.movieschallenge.data.network.response.UpcomingResponse
import com.luismiguel.movieschallenge.data.network.response.toDomain
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import java.lang.Exception

@ExperimentalCoroutinesApi
class RemoteRepositoryImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val movieApiService = mockk<MovieApiService>()

    private val remoteRepository = RemoteRepositoryImpl(movieApiService)

    @Test
    fun `getPlayingMovies success`() = runBlocking {
        val datesResponse = DatesResponse("2024-03-13", "2024-02-21")
        val movieResultReponse = MovieResultResponse(false, null, 1, "en", "Madame Web", "Forced to confront revelations about her past...", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val upcomingResponse = UpcomingResponse(datesResponse, 1, listOf(movieResultReponse), 1, 1)
        //Given
        coEvery { movieApiService.getNowPlayingMovies(1, API_KEY) } returns Response.success(upcomingResponse)
        val model = upcomingResponse.toDomain()

        //When
        val result = remoteRepository.getNowPlayingMovies(1, API_KEY)
        lateinit var resultCollect: DataStatusRemote<UpcomingModel>
        result.collect{
            resultCollect = it
        }

        //Then
        assertThat("getPlayingMovies success", resultCollect.data, equalTo(model))

    }

    @Test
    fun `getPlayingMovies error`() = runBlocking {
        val errorMessage = "Mensaje de error"
        val errorCode = 404
        val errorBody = errorMessage.toResponseBody("text/plain".toMediaTypeOrNull())

        //Given
        coEvery { movieApiService.getNowPlayingMovies(1, API_KEY) } returns Response.error(errorCode, errorBody)

        //When
        val result = remoteRepository.getNowPlayingMovies(1, API_KEY)

        lateinit var resultCollect: DataStatusRemote<UpcomingModel>
        result.collect{
            resultCollect = it
        }

        assertThat("getPlayingMovies failure", resultCollect.code, equalTo(errorCode))
    }

    @Test
    fun `getPlayingMovies throws Exception`() = runBlocking {
        val emessage = "Exception text"
        val exception = Exception(emessage)
        //Given
        coEvery { movieApiService.getNowPlayingMovies(1, API_KEY) } throws Exception(exception)

        //When
        val result = remoteRepository.getNowPlayingMovies(1, API_KEY)

        lateinit var resultCollect: DataStatusRemote<UpcomingModel>
        result.collect{
            resultCollect = it
        }

        //Then
        assertThat("getPlayingMovies exception", resultCollect.message, equalTo(exception.toString()))
    }
}
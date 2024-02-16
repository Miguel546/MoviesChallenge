package com.luismiguel.movieschallenge.ui.home.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luismiguel.movieschallenge.MainDispatcherRule
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.domain.model.DatesModel
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import com.luismiguel.movieschallenge.domain.usecase.remote.GetNowMoviesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UpcomingViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var getNowMoviesUseCase = mockk<GetNowMoviesUseCase>()

    private lateinit var upcomingViewModel: UpcomingViewModel

    @Before
    fun setup(){
        upcomingViewModel = UpcomingViewModel(getNowMoviesUseCase)
    }

    @Test
    fun `callMovies success`() = runBlocking{
        val datesModel = DatesModel("2024-03-13", "2024-02-21")
        val movieModel = MovieModel(false, null, 1, "en", "Madame Web", "Forced to confront revelations about her past...", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val upcomingModel = UpcomingModel(datesModel, 1, listOf(movieModel), 1, 1)
        val dataStatusRemote = DataStatusRemote.success(200, upcomingModel)
        val flow = flow {
            emit(dataStatusRemote)
        }
        coEvery { getNowMoviesUseCase.getNowMovies(1) } returns flow

        upcomingViewModel.callMovies()

        assertThat(upcomingViewModel.upcomingMovies.value, equalTo(dataStatusRemote))
    }
}
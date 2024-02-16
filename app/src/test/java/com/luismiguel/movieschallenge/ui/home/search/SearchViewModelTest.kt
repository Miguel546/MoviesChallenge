package com.luismiguel.movieschallenge.ui.home.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luismiguel.movieschallenge.MainDispatcherRule
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.usecase.local.GetAllMoviesUseCase
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
class SearchViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private var getAllMoviesUseCase = mockk<GetAllMoviesUseCase>()

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setup(){
        searchViewModel = SearchViewModel(getAllMoviesUseCase)
    }

    @Test
    fun `getMovies Success`() = runBlocking{
        val movieModel = MovieModel(false, null, 1, "en", "Madame Web", "Forced to confront revelations about her past...", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val listMovieModel = listOf(movieModel)
        val flow = flow { emit (listMovieModel) }
        coEvery { getAllMoviesUseCase.invoke() } returns flow

        searchViewModel.getMovies()

        assertThat(searchViewModel.moviesDBList.value.data, equalTo(listMovieModel))

    }


}
package com.luismiguel.movieschallenge.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.luismiguel.movieschallenge.data.dao.MovieFlowDao
import com.luismiguel.movieschallenge.data.entities.MovieEntity
import com.luismiguel.movieschallenge.data.entities.toDatabase
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import com.luismiguel.movieschallenge.domain.model.toDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Rule
import org.junit.Test
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertNull
import java.lang.Exception

@ExperimentalCoroutinesApi
class LocalRepositoryImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val movieDao = mockk<MovieFlowDao>()

    private val localRepository = LocalRepositoryImpl(movieDao)

    @Test
    fun `addMovieDB`() = runBlocking {
        val movieModel = MovieModel(false, "/uUiIGztTrfDhPdAFJpr6m4UBMAd.jpg", 1, "es", "Madame Web", "Forced to confront revelations about her past", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        //Given
        coEvery { movieDao.add(movieModel.toDatabase()) } returns Unit

        //When
        localRepository.addMovieDB(movieModel)
    }

    @Test
    fun `getAllMoviesDB success`() = runBlocking {
        val movieModel = MovieModel(false, "/uUiIGztTrfDhPdAFJpr6m4UBMAd.jpg", 1, "es", "Madame Web", "Forced to confront revelations about her past", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val flow = flow {
            emit(listOf(movieModel.toDatabase()))
        }
        //Given
        coEvery { movieDao.getAllMoviesDB() } returns flow

        //When
        val result = localRepository.getAllMoviesDB()

        lateinit var resultCollect: List<MovieModel>
        result.collect{
            resultCollect = it
        }

        lateinit var flowCollect : List<MovieEntity>
        flow.collect{
            flowCollect = it
        }

        val flowToDomain = flowCollect.map { it.toDomain() }
        //Then
        assertThat(
            "getAllMoviesDB success",
            resultCollect,
            equalTo(flowToDomain)
        )
    }

    @Test
    fun `getAllMoviesDB throws exception`() = runBlocking {
        val emessage = "Exception text"
        val exception = Exception(emessage)
        //Given
        coEvery { movieDao.getAllMoviesDB() } throws exception

        //When
        val result = localRepository.getAllMoviesDB()

        var resultCatch: Throwable ?= null
        var resultCollect: List<MovieModel>? = null

        result.catch{
            resultCatch = it
        }.collect{
            resultCollect = it
        }

        //Then
        assertNull(resultCollect)
    }

    @Test
    fun `getMovieDB`() = runBlocking {
        val movieModel = MovieModel(false, "/uUiIGztTrfDhPdAFJpr6m4UBMAd.jpg", 1, "es", "Madame Web", "Forced to confront revelations about her past", 794.984, "/rULWuutDcN5NvtiZi4FRPzRYWSh.jpg", "2024-02-14", "Madame Web", false, 5.711, 95)
        val flow = flow {
            emit(listOf(movieModel.toDatabase()))
        }
        //Given
        coEvery { movieDao.getMovie(1) } returns flow

        //When
        val result = localRepository.getMovieDB(1)

        lateinit var resultCollect: List<MovieModel>
        result.collect{
            resultCollect = it
        }

        lateinit var flowCollect : List<MovieEntity>
        flow.collect{
            flowCollect = it
        }

        val flowToDomain = flowCollect.map { it.toDomain() }
        //Then
        assertThat(
            "getMovieDB success",
            resultCollect,
            equalTo(flowToDomain)
        )
    }
}
package com.luismiguel.movieschallenge.domain.usecase.remote

import com.luismiguel.movieschallenge.data.dao.MovieFlowDao
import com.luismiguel.movieschallenge.domain.repository.IRemoteRepository
import com.luismiguel.movieschallenge.domain.usecase.FakeRemoteRepositoryAndroid
import kotlinx.coroutines.flow.collect
import org.hamcrest.MatcherAssert.assertThat
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class GetNowMoviesUseCaseTest {
    private val fakeRemoteRepository = FakeRemoteRepositoryAndroid()
    private val getNowMoviesUseCase = GetNowMoviesUseCase(fakeRemoteRepository)

    @Test
    fun `getNowMovies`() = runBlocking {
        val result = getNowMoviesUseCase.getNowMovies(1)
        var code =  0
        result.collect{
            code = it.code
        }
        assertThat("getNowMovies", 200, equalTo(code))
    }
}
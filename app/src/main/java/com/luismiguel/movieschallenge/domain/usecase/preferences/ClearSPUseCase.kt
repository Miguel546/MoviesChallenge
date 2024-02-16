package com.luismiguel.movieschallenge.domain.usecase.preferences

import com.luismiguel.movieschallenge.data.preferences.MovieSharedPreferences
import javax.inject.Inject

class ClearSPUseCase  @Inject constructor(private val movieSharedPreferences: MovieSharedPreferences){
    operator fun invoke() {
        movieSharedPreferences.clear()
    }
}
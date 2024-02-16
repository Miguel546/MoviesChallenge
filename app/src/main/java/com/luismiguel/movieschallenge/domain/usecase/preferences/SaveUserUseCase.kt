package com.luismiguel.movieschallenge.domain.usecase.preferences

import com.luismiguel.movieschallenge.data.preferences.MovieSharedPreferences
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(private val movieSharedPreferences: MovieSharedPreferences){
    operator fun invoke(user: String) {
        movieSharedPreferences.saveUser(user)
    }
}
package com.luismiguel.movieschallenge.ui.splash

import androidx.lifecycle.ViewModel
import com.luismiguel.movieschallenge.domain.usecase.preferences.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel() {
    fun getUser(): String? {
        return getUserUseCase.invoke()
    }
}
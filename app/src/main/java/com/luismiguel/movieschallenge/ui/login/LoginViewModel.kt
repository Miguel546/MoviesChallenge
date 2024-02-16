package com.luismiguel.movieschallenge.ui.login

import androidx.lifecycle.ViewModel
import com.luismiguel.movieschallenge.domain.usecase.preferences.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val saveUserUseCase: SaveUserUseCase): ViewModel() {
    fun savesp(user: String){
        saveUserUseCase.invoke(user)
    }
}
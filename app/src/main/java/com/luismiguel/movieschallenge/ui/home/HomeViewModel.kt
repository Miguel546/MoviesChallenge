package com.luismiguel.movieschallenge.ui.home

import androidx.lifecycle.ViewModel
import com.luismiguel.movieschallenge.domain.usecase.preferences.ClearSPUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val clearSPUseCase: ClearSPUseCase): ViewModel() {
    private val _numeroFragments = MutableStateFlow<Int>(0)
    val numeroFragments : StateFlow<Int> = _numeroFragments

    fun clearPreferences(){
        clearSPUseCase.invoke()
    }

    fun putNumeroFragments(numero: Int){
        _numeroFragments.value = numero
    }
}
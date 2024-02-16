package com.luismiguel.movieschallenge.ui.home.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusRemote
import com.luismiguel.movieschallenge.domain.model.UpcomingModel
import com.luismiguel.movieschallenge.domain.usecase.remote.GetNowMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpcomingViewModel @Inject constructor(
    private val getNowMoviesUseCase: GetNowMoviesUseCase
): ViewModel() {

    init{
        callMovies()
    }

    private val _upcomingMovies = MutableStateFlow<DataStatusRemote<UpcomingModel?>>(DataStatusRemote.loading())
    val upcomingMovies : StateFlow<DataStatusRemote<UpcomingModel?>> = _upcomingMovies

    fun callMovies(page: Int = 1) {
        viewModelScope.launch {
            getNowMoviesUseCase.getNowMovies(page).collect {
                _upcomingMovies.value = it
            }
        }
    }

}
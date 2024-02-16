package com.luismiguel.movieschallenge.ui.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusLocal
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.usecase.local.AddMovieUseCase
import com.luismiguel.movieschallenge.domain.usecase.local.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getMovieUseCase: GetMovieUseCase,
    private val addMovieUseCase: AddMovieUseCase
) : ViewModel() {

    private val _upcomingMovie = MutableStateFlow<DataStatusLocal<List<MovieModel>>>(DataStatusLocal.loading())
    val upcomingMovie : StateFlow<DataStatusLocal<List<MovieModel>>> = _upcomingMovie
    fun getMovieById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _upcomingMovie.value = DataStatusLocal.loading()
            getMovieUseCase.invoke(id).catch { _upcomingMovie.value = DataStatusLocal.error(it.message.toString()) }
                .collect {_upcomingMovie.value = DataStatusLocal.success(it)}
        }
    }

    fun addMovieDB(movie: MovieModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addMovieUseCase.invoke(movie)
        }
    }
}
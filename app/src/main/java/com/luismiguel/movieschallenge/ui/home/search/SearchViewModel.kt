package com.luismiguel.movieschallenge.ui.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.luismiguel.movieschallenge.data.network.datastatus.DataStatusLocal
import com.luismiguel.movieschallenge.domain.model.MovieModel
import com.luismiguel.movieschallenge.domain.usecase.local.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getMoviesUseCase: GetAllMoviesUseCase): ViewModel() {

    private val _moviesDBList = MutableStateFlow<DataStatusLocal<List<MovieModel>>>(DataStatusLocal.loading())
    val moviesDBList : StateFlow<DataStatusLocal<List<MovieModel>>> = _moviesDBList

    init{
        getMovies()
    }

    fun getMovies(){
        viewModelScope.launch {
            _moviesDBList.value = DataStatusLocal.loading()
            getMoviesUseCase.invoke()
                .catch {
                    _moviesDBList.value = DataStatusLocal.error(it.message.toString())
                }
                .collect {
                    _moviesDBList.value = DataStatusLocal.success(it)
                }
        }
    }
}
package com.franco.moviesdb.ui.movie.comedy

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.repository.movieComedyRepository.MovieComedyRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch

class MovieComedyViewModel @ViewModelInject constructor(
        private val movieComedyRepository: MovieComedyRepository,
) : ViewModel() {

    private val _spinnerMovieComedy = MutableStateFlow(true)
    val spinnerMovieComedy: StateFlow<Boolean> get() = _spinnerMovieComedy

    val searchMovieComedyQuery = MutableStateFlow("")

    @ExperimentalCoroutinesApi
    private val movieComedyQueryFlow = searchMovieComedyQuery.flatMapLatest {
        movieComedyRepository.getMovieListComedyByQuery(it)
    }
    val movieComedyQuery = movieComedyQueryFlow.asLiveData()
    init {
        viewModelScope.launch {
        notifyLastVisible(0)
        }
    }
    fun notifyLastVisible(lastVisible: Int) {
        Log.i("stac", "viewModel")
        viewModelScope.launch {
            movieComedyRepository.checkRequireNewPageMovieComedy(lastVisible)
            _spinnerMovieComedy.value = false
        }
    }
}
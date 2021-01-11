package com.franco.moviesdb.ui.movie.action

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.franco.moviesdb.repository.movieActionRepository.MovieActionRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch


class MovieActionViewModel @ViewModelInject constructor(
        private val movieActionRepository: MovieActionRepository
) : ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner


    val searchQuery = MutableStateFlow("")

    private val movieQueryFlow = searchQuery.flatMapLatest {
        movieActionRepository.getMovieListActionByQuery(it)
    }

    val movieQuery = movieQueryFlow.asLiveData()

    init {
        viewModelScope.launch { notifyLastVisible(0) }
    }

    fun notifyLastVisible(lastVisible: Int) {
        viewModelScope.launch {
            movieActionRepository.checkRequireNewPageMovieAction(lastVisible)
            _spinner.value = false
        }
    }
}